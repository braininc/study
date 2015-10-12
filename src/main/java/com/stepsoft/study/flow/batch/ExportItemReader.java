package com.stepsoft.study.flow.batch;

import com.stepsoft.study.data.entity.Sinner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.IN_EXPORT_PROCESSING_READER_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@Component
public class ExportItemReader extends PollableChannelItemReader<Sinner> {

    @Value("${flow.exportItemReader.timeout}")
    private int timeout;

    @Autowired
    @Qualifier(IN_EXPORT_PROCESSING_READER_CHANNEL)
    private PollableChannel channel;

    @PostConstruct
    private void init() {

        setChannel(channel);
        setTimeout(timeout);
    }
}
