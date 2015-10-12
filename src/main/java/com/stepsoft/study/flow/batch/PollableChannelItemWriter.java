package com.stepsoft.study.flow.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.PollableChannel;

import java.util.List;

import static java.lang.Long.MAX_VALUE;

/**
 * @author Eugene Stepanenkov
 */
public abstract class PollableChannelItemWriter<T> implements ItemWriter<T> {

    private PollableChannel channel;
    private long timeout = MAX_VALUE;

    public PollableChannel getChannel() {
        return channel;
    }

    public void setChannel(PollableChannel channel) {
        this.channel = channel;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public void write(List<? extends T> items) throws Exception {

        getChannel().send(MessageBuilder.withPayload(items).build(), timeout);
    }
}
