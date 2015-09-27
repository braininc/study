package com.stepsoft.study.configuration.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_BULK_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_BULK_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.ADDING_MODEL_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.FINDING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.FINDING_MODEL_REPLY_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_DB_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.MODIFYING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.REMOVING_MODEL_CHANNEL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.REPLY_DB_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
public class ChannelContext {

    @Bean(name = ADDING_MODEL_CHANNEL)
    public QueueChannel addingModelChannel() {

        return new QueueChannel();
    }

    @Bean(name = ADDING_MODEL_BULK_CHANNEL)
    public QueueChannel addingModelBulkChannel() {

        return new QueueChannel();
    }

    @Bean(name = MODIFYING_MODEL_CHANNEL)
    public QueueChannel modifyingModelChannel() {

        return new QueueChannel();
    }

    @Bean(name = REMOVING_MODEL_CHANNEL)
    public QueueChannel removingModelChannel() {

        return new QueueChannel();
    }

    @Bean(name = FINDING_MODEL_CHANNEL)
    public QueueChannel findingModelChannel() {

        return new QueueChannel();
    }

    @Bean(name = ADDING_MODEL_REPLY_CHANNEL)
    public QueueChannel addingModelReplyChannel() {

        return new QueueChannel();
    }

    @Bean(name = ADDING_MODEL_BULK_REPLY_CHANNEL)
    public QueueChannel addingModelBulkReplyChannel() {

        return new QueueChannel();
    }

    @Bean(name = FINDING_MODEL_REPLY_CHANNEL)
    public QueueChannel findingModelReplyChannel() {

        return new QueueChannel();
    }

    @Bean(name = REPLY_DB_CHANNEL)
    public QueueChannel replyDbChannel() {

        return new QueueChannel();
    }

    @Bean(name = IN_DB_CHANNEL)
    public QueueChannel inDbChannel() {

        return new QueueChannel();
    }
}