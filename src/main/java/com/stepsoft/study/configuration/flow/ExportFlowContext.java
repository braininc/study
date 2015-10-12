package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.configuration.annotation.JobGateway;
import com.stepsoft.study.configuration.annotation.MailOutboundChannelAdapter;
import com.stepsoft.study.configuration.annotation.MessageHeaderEnricher;
import com.stepsoft.study.flow.messaging.ExportAction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.integration.jpa.inbound.JpaPollingChannelAdapter;
import org.springframework.integration.mail.MailSendingMessageHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.splitter.DefaultMessageSplitter;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.HashMap;
import java.util.Map;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.EXPORT_ACTION;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.EXPORT_JOB;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_POLLER;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_PROCESSING_LAUNCH_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_PROCESSING_READER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_SPLITTER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_TRANSFORMATION_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_EXPORT_NOTIFICATION_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_EXPORT_NOTIFICATION_HEADER_ENRICHER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_EXPORT_PROCESSING_CHANNEL;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.integration.mail.MailHeaders.FROM;
import static org.springframework.integration.mail.MailHeaders.SUBJECT;
import static org.springframework.integration.mail.MailHeaders.TO;
import static org.springframework.integration.scheduling.PollerMetadata.DEFAULT_POLLER;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@Import(ExportChannelContext.class)
@EnableBatchProcessing
@ComponentScan(basePackages = "com.stepsoft.study.flow.batch")
@PropertySource("classpath:flow.properties")
@PropertySource("classpath:mail.properties")
public class ExportFlowContext {

    public static final String EXPORT_STEP = "exportStep";

    @Value("${flow.defaultPoller.fixedDelay}")
    private int fixedDelay;

    @Value("${flow.defaultPoller.maxMessagesPerPoll}")
    private int maxMessagesPerPoll;

    @Value("${flow.inExportPoller.fixedDelay}")
    private int exportFixedDelay;

    @Value("${flow.inExportPoller.maxMessagesPerPoll}")
    private int exportMaxMessagesPerPoll;

    @Value("${flow.exportChunk.size}")
    private int chunkSize;

    @Value("${mail.message.subject}")
    private String mailSubject;

    @Value("${mail.message.from}")
    private String mailFrom;

    @Value("${mail.message.to}")
    private String mailTo;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean(name = DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {

        PollerMetadata metadata = new PollerMetadata();
        metadata.setTrigger(new PeriodicTrigger(fixedDelay, MILLISECONDS));
        metadata.setMaxMessagesPerPoll(maxMessagesPerPoll);

        return metadata;
    }

    @Autowired
    @Bean(name = IN_EXPORT_POLLER)
    public PollerMetadata inExportPoller(PlatformTransactionManager manager) {

        MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
        attributeSource.setTransactionAttribute(new DefaultTransactionAttribute());
        TransactionInterceptor interceptor = new TransactionInterceptor(manager, attributeSource);

        PollerMetadata metadata = new PollerMetadata();
        metadata.setTrigger(new PeriodicTrigger(exportFixedDelay, MILLISECONDS));
        metadata.setMaxMessagesPerPoll(exportMaxMessagesPerPoll);
        metadata.setAdviceChain(singletonList(interceptor));
        return metadata;
    }

    @Bean
    @Splitter(inputChannel = IN_EXPORT_SPLITTER_CHANNEL)
    public DefaultMessageSplitter inExportSplitter() {

        DefaultMessageSplitter splitter = new DefaultMessageSplitter();
        splitter.setOutputChannelName(IN_EXPORT_PROCESSING_READER_CHANNEL);

        return splitter;
    }

    @Bean
    @Autowired
    @InboundChannelAdapter(
            value = IN_EXPORT_SPLITTER_CHANNEL,
            poller = @Poller(IN_EXPORT_POLLER)
    )
    public JpaPollingChannelAdapter inExportAdapter(JpaExecutor exportFetchJpaExecutor) {

        return new JpaPollingChannelAdapter(exportFetchJpaExecutor);
    }

    @Bean
    @Autowired
    @JobGateway(inputChannel = IN_EXPORT_PROCESSING_LAUNCH_CHANNEL)
    public JobLaunchingGateway jobLaunchingGateway(JobLauncher jobLauncher) {

        return new JobLaunchingGateway(jobLauncher);
    }

    @Autowired
    @Bean(name = EXPORT_JOB)
    public Job exportJob(Step export) {
        return jobs.get(EXPORT_JOB).start(export).build();
    }

    @Bean
    @Autowired
    @MailOutboundChannelAdapter(inputChannel = OUT_EXPORT_NOTIFICATION_CHANNEL)
    public MailSendingMessageHandler outExportNotificationOutboundChannelAdapter(JavaMailSender mailSender) {

        return new MailSendingMessageHandler(mailSender);
    }

    @Autowired
    @Bean(name = EXPORT_STEP)
    @SuppressWarnings("unchecked")
    public Step export(ItemReader reader,
                       ItemProcessor processor,
                       ItemWriter writer) {

        return steps.get(EXPORT_STEP)
                .chunk(chunkSize)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @MessageHeaderEnricher(
            inputChannel = OUT_EXPORT_NOTIFICATION_HEADER_ENRICHER_CHANNEL,
            outputChannel = OUT_EXPORT_NOTIFICATION_CHANNEL
    )
    public HeaderEnricher outExportNotificationHeaderEnricher() {

        Map<String, StaticHeaderValueMessageProcessor<?>> headers = new HashMap<>();
        headers.put(SUBJECT, new StaticHeaderValueMessageProcessor<>(mailSubject));
        headers.put(FROM, new StaticHeaderValueMessageProcessor<>(mailFrom));
        headers.put(TO, new StaticHeaderValueMessageProcessor<>(mailTo));

        return new HeaderEnricher(headers);
    }

    @MessageEndpoint
    public static class InExportEndpoints {

        @Autowired
        private Job exportJob;

        @Router(inputChannel = IN_EXPORT_CHANNEL)
        public String toProcessingChannels(@Header(name = EXPORT_ACTION) ExportAction action) {

            switch (action) {

                case REPORT:
                    return IN_EXPORT_TRANSFORMATION_CHANNEL;

                default:
                    throw new IllegalStateException();
            }
        }

        @Transformer(
                inputChannel = IN_EXPORT_TRANSFORMATION_CHANNEL,
                outputChannel = IN_EXPORT_PROCESSING_LAUNCH_CHANNEL
        )
        public JobLaunchRequest fromStringToJobLaunchRequest() {

            JobParametersBuilder builder = new JobParametersBuilder();
            return new JobLaunchRequest(exportJob, builder.toJobParameters());
        }
    }

    @MessageEndpoint
    public static class OutExportEndpoints {

        @Transformer(
                inputChannel = OUT_EXPORT_PROCESSING_CHANNEL,
                outputChannel = OUT_EXPORT_NOTIFICATION_HEADER_ENRICHER_CHANNEL
        )
        public Message<String> fromListOfSinnerSummaryModelsToString(Message<String> message) {

            return message;
        }
    }
}
