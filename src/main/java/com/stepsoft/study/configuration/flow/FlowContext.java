package com.stepsoft.study.configuration.flow;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

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
@Import({
        DataContext.class,
        ImportFlowContext.class,
        ImportChannelContext.class
})
public class FlowContext {
}