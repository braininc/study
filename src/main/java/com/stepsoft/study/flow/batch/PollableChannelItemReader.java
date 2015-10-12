package com.stepsoft.study.flow.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.messaging.PollableChannel;

import static java.lang.Long.MAX_VALUE;

/**
 * @author Eugene Stepanenkov
 */
public abstract class PollableChannelItemReader<T> implements ItemReader<T> {

    private long timeout = MAX_VALUE;
    private PollableChannel channel;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public PollableChannel getChannel() {
        return channel;
    }

    public void setChannel(PollableChannel channel) {
        this.channel = channel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T read() throws Exception {

        return (T) getChannel().receive(timeout);
    }
}
