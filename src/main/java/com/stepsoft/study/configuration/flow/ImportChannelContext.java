package com.stepsoft.study.configuration.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_DELETE_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_FETCH_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_POST_PERSIST_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_PRE_PERSIST_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_IMPORT_TRANSFORMATION_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_IMPORT_TRANSFORMATION_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
public class ImportChannelContext {

    @Bean(name = IN_IMPORT_CHANNEL)
    public DirectChannel inImportChannel() {

        return new DirectChannel();
    }

    @Bean(name = IN_IMPORT_TRANSFORMATION_CHANNEL)
    public DirectChannel inImportTransformationChannel() {

        return new DirectChannel();
    }

    @Bean(name = IN_IMPORT_DB_CHANNEL)
    public DirectChannel inImportDbChannel() {

        return new DirectChannel();
    }

    @Bean(name = IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL)
    public DirectChannel inImportAddOrUpdateDbChannel() {

        return new DirectChannel();
    }

    @Bean(name = IN_IMPORT_FETCH_DB_CHANNEL)
    public DirectChannel inImportFetchDbChannel() {

        return new DirectChannel();
    }

    @Bean(name = IN_IMPORT_DELETE_DB_CHANNEL)
    public DirectChannel inImportDeleteDbChannel() {

        return new DirectChannel();
    }

    @Bean(name = IN_IMPORT_PRE_PERSIST_CHANNEL)
    public DirectChannel inImportPrePersistChannel() {

        return new DirectChannel();
    }

    @Bean(name = IN_IMPORT_POST_PERSIST_CHANNEL)
    public DirectChannel inImportPostPersistChannel() {

        return new DirectChannel();
    }

    @Bean(name = OUT_IMPORT_CHANNEL)
    public DirectChannel outImportChannel() {

        return new DirectChannel();
    }

    @Bean(name = OUT_IMPORT_DB_CHANNEL)
    public MessageChannel outImportDbChannel() {

        return new DirectChannel();
    }

    @Bean(name = OUT_IMPORT_TRANSFORMATION_CHANNEL)
    public DirectChannel outImportTransformationChannel() {

        return new DirectChannel();
    }
}