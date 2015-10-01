package com.stepsoft.study.configuration.flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.support.management.DefaultMetricsFactory;
import org.springframework.integration.support.management.MetricsFactory;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.FLOW_METRICS_FACTORY;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.integration.scheduling.PollerMetadata.DEFAULT_POLLER;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@EnableIntegration
@EnableIntegrationManagement(
        defaultLoggingEnabled = "${flow.monitoring.loggingEnabled}",
        defaultCountsEnabled = "${flow.monitoring.countsEnabled}",
        defaultStatsEnabled = "${flow.monitoring.statsEnabled}",
        countsEnabled = "${flow.monitoring.countsPatterns}",
        statsEnabled = "${flow.monitoring.statsPatterns}",
        metricsFactory = FLOW_METRICS_FACTORY
)
@IntegrationComponentScan(basePackages = {
        "com.stepsoft.study.flow",
        "com.stepsoft.study.configuration.flow",
        "com.stepsoft.study.flow.messaging"
})
@ComponentScan(basePackages = {
        "com.stepsoft.study.flow",
        "com.stepsoft.study.configuration.flow",
        "com.stepsoft.study.flow.messaging"
})
@Import({ImportFlowContext.class})
@PropertySource("classpath:flow.properties")
public class FlowContext {

    @Value("${flow.defaultPoller.fixedDelay}")
    private int fixedDelay;

    @Value("${flow.defaultPoller.maxMessagesPerPoll}")
    private int maxMessagesPerPoll;

    @Bean(name = DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {

        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(fixedDelay, MILLISECONDS));
        pollerMetadata.setMaxMessagesPerPoll(maxMessagesPerPoll);

        return pollerMetadata;
    }

    @Bean(name = FLOW_METRICS_FACTORY)
    public MetricsFactory flowMetricsFactory() {

        return new DefaultMetricsFactory();
    }

    @Autowired
    @Bean(name = DEFAULT_POLLER + "777")
    public PollerMetadata defaultPoller(PlatformTransactionManager manager) {

        MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
        attributeSource.setTransactionAttribute(new DefaultTransactionAttribute());
        TransactionInterceptor interceptor = new TransactionInterceptor(manager, attributeSource);

        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(fixedDelay, MILLISECONDS));
        pollerMetadata.setMaxMessagesPerPoll(maxMessagesPerPoll);
        pollerMetadata.setAdviceChain(singletonList(interceptor));

        return pollerMetadata;
    }
}