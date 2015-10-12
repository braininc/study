package com.stepsoft.study.configuration.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_PROCESSING_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_PROCESSING_LAUNCH_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_PROCESSING_READER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_SPLITTER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_TRANSFORMATION_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_EXPORT_NOTIFICATION_HEADER_ENRICHER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_EXPORT_NOTIFICATION_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_EXPORT_PROCESSING_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
public class ExportChannelContext {

    @Bean(name = IN_EXPORT_CHANNEL)
    public QueueChannel inExportChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_EXPORT_TRANSFORMATION_CHANNEL)
    public QueueChannel inExportTransformationChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_EXPORT_PROCESSING_CHANNEL)
    public QueueChannel inExportProcessingChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_EXPORT_SPLITTER_CHANNEL)
    public QueueChannel inExportSplitterChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_EXPORT_PROCESSING_LAUNCH_CHANNEL)
    public QueueChannel inExportProcessingLaunchChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_EXPORT_PROCESSING_READER_CHANNEL)
    public QueueChannel inExportProcessingReaderChannel() {

        return new QueueChannel();
    }

    @Bean(name = OUT_EXPORT_PROCESSING_CHANNEL)
    public QueueChannel outExportProcessingChannel() {

        return new QueueChannel();
    }

    @Bean(name = OUT_EXPORT_NOTIFICATION_CHANNEL)
    public QueueChannel outExportNotificationChannel() {

        return new QueueChannel();
    }

    @Bean(name = OUT_EXPORT_NOTIFICATION_HEADER_ENRICHER_CHANNEL)
    public QueueChannel outExportHeaderEnricherChannel() {

        return new QueueChannel();
    }
}
