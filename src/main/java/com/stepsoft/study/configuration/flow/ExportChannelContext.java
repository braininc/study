package com.stepsoft.study.configuration.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
public class ExportChannelContext {

    @Bean(name = IN_EXPORT_CHANNEL)
    private QueueChannel inExportChannel() {

        return new QueueChannel();
    }
}
