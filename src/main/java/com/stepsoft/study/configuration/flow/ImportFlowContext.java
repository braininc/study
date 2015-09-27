package com.stepsoft.study.configuration.flow;

import com.stepsoft.study.configuration.annotation.JpaGateway;
import com.stepsoft.study.flow.ProcessingService;
import com.stepsoft.study.flow.messaging.ImportAction;
import com.stepsoft.study.mvc.model.RestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.annotation.BridgeTo;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ReleaseStrategy;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.jpa.core.JpaExecutor;
import org.springframework.integration.jpa.outbound.JpaOutboundGateway;
import org.springframework.integration.splitter.DefaultMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;
import java.util.Set;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.BULK_SIZE;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IMPORT_ACTION;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DELETE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_FETCH_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_PROCESSING_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_SPLITTER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_AGGREGATOR_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_PROCESSING_CHANNEL;
import static java.lang.Integer.parseInt;
import static org.springframework.integration.jpa.support.OutboundGatewayType.RETRIEVING;
import static org.springframework.integration.jpa.support.OutboundGatewayType.UPDATING;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan(basePackages = {
        "com.stepsoft.study.flow",
        "com.stepsoft.study.configuration.flow"
})
@ComponentScan(basePackages = {
        "com.stepsoft.study.flow",
        "com.stepsoft.study.configuration.flow"
})
@Import({DataContext.class, ImportChannelContext.class})
public class ImportFlowContext {

    @MessageEndpoint
    public static class InImportRouterEndpoint {

        @Router(inputChannel = IN_IMPORT_CHANNEL)
        public String route(@Header(name = IMPORT_ACTION) ImportAction action) {

            switch (action) {

                case ADD_BULK:
                    return IN_IMPORT_SPLITTER_CHANNEL;

                default:
                    return IN_IMPORT_DB_CHANNEL;
            }
        }
    }

    @Bean
    @Splitter(inputChannel = IN_IMPORT_SPLITTER_CHANNEL)
    public DefaultMessageSplitter inImportSplitter() {

        DefaultMessageSplitter splitter = new DefaultMessageSplitter();
        splitter.setOutputChannelName(IN_IMPORT_PROCESSING_CHANNEL);

        return splitter;
    }

    @MessageEndpoint
    public static class ProcessingServiceEndpoint {

        @Autowired
        private ProcessingService serviceActivator;

        @ServiceActivator(inputChannel = IN_IMPORT_PROCESSING_CHANNEL, outputChannel = OUT_IMPORT_PROCESSING_CHANNEL)
        public Message<RestModel> serviceMethod(Message<RestModel> message) {
            return serviceActivator.service(message);
        }
    }

    @Bean
    @BridgeFrom(OUT_IMPORT_PROCESSING_CHANNEL)
    public MessageChannel afterProcessingBridgeFrom() {

        return new DirectChannel();
    }

    @Bean
    @BridgeTo(IN_IMPORT_DB_CHANNEL)
    public MessageChannel afterProcessingBridgeTo() {

        return new DirectChannel();
    }

    @MessageEndpoint
    public static class InImportDbRouterEndpoint {

        @Router(inputChannel = IN_IMPORT_DB_CHANNEL)
        public String route(@Header(name = IMPORT_ACTION) ImportAction action) {

            switch (action) {

                case DELETE:
                    return IN_IMPORT_DELETE_DB_CHANNEL;

                case FETCH:
                    return IN_IMPORT_FETCH_DB_CHANNEL;

                default:
                    return IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL;
            }
        }
    }

    @Bean
    @Autowired
    @JpaGateway(inputChannel = IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL)
    public JpaOutboundGateway inImportAddOrUpdateDbGateway(JpaExecutor importAddOrUpdateJpaExecutor) {

        JpaOutboundGateway gateway = new JpaOutboundGateway(importAddOrUpdateJpaExecutor);
        gateway.setOutputChannelName(OUT_IMPORT_DB_CHANNEL);
        gateway.setGatewayType(UPDATING);

        return gateway;
    }

    @Bean
    @Autowired
    @JpaGateway(inputChannel = IN_IMPORT_FETCH_DB_CHANNEL)
    public JpaOutboundGateway inImportFetchDbGateway(JpaExecutor importFetchJpaExecutor) {

        JpaOutboundGateway gateway = new JpaOutboundGateway(importFetchJpaExecutor);
        gateway.setOutputChannelName(OUT_IMPORT_DB_CHANNEL);
        gateway.setGatewayType(RETRIEVING);

        return gateway;
    }

    @MessageEndpoint
    public static class OutImportDbRouterEndpoint {

        @Router(inputChannel = OUT_IMPORT_DB_CHANNEL)
        public String route(@Header(name = IMPORT_ACTION) ImportAction action) {

            switch (action) {

                case ADD_BULK:
                    return OUT_IMPORT_AGGREGATOR_CHANNEL;

                default:
                    return OUT_IMPORT_CHANNEL;
            }
        }
    }

    @Bean
    @Autowired
    @JpaGateway(inputChannel = IN_IMPORT_DELETE_DB_CHANNEL)
    public JpaOutboundGateway inImportDeleteDbGateway(JpaExecutor importDeleteJpaExecutor) {

        JpaOutboundGateway gateway = new JpaOutboundGateway(importDeleteJpaExecutor);
        gateway.setOutputChannelName(OUT_IMPORT_DB_CHANNEL);
        gateway.setGatewayType(UPDATING);

        return gateway;
    }

    @MessageEndpoint
    public static class OutImportAggregatorEndpoint {

        @Aggregator(inputChannel = OUT_IMPORT_AGGREGATOR_CHANNEL, outputChannel = OUT_IMPORT_CHANNEL)
        public Set<Long> aggregate(Set<Long> ids) {

            return ids;
        }

        @ReleaseStrategy
        public boolean releaseStrategy(List<Message<Long>> messages) {

            int bulkSize = parseInt((String) messages.get(0).getHeaders().get(BULK_SIZE));
            return bulkSize == messages.size();
        }

        @CorrelationStrategy
        public String correlationStrategy(@Header(name = BULK_SIZE) String bulkSize) {

            return bulkSize;
        }

    }
}
