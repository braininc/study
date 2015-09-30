package com.stepsoft.study.configuration.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DELETE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_FETCH_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_SPLITTER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_TRANSFORMER_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_AGGREGATOR_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_TRANSFORMER_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
public class ImportChannelContext {

    @Bean(name = IN_IMPORT_CHANNEL)
    public QueueChannel inImportChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_IMPORT_TRANSFORMER_CHANNEL)
    public QueueChannel inImportTransformerChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_IMPORT_DB_CHANNEL)
    public QueueChannel inImportDbChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_IMPORT_SPLITTER_CHANNEL)
    public QueueChannel inImportSplitterChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL)
    public QueueChannel inImportAddOrUpdateDbChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_IMPORT_FETCH_DB_CHANNEL)
    public QueueChannel inImportFetchDbChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_IMPORT_DELETE_DB_CHANNEL)
    public QueueChannel inImportDeleteDbChannel() {

        return new QueueChannel();
    }

    @Bean(name = OUT_IMPORT_CHANNEL)
    public QueueChannel outImportChannel() {

        return new QueueChannel();
    }

    @Bean(name = OUT_IMPORT_DB_CHANNEL)
    public QueueChannel outImportDbChannel() {

        return new QueueChannel();
    }

    @Bean(name = OUT_IMPORT_TRANSFORMER_CHANNEL)
    public QueueChannel outImportTransformerChannel() {

        return new QueueChannel();
    }

    @Bean(name = OUT_IMPORT_AGGREGATOR_CHANNEL)
    public QueueChannel outImportAggregatorChannel() {

        return new QueueChannel();
    }
}