package com.stepsoft.study.flow.batch;

import com.stepsoft.study.mvc.model.SinnerSummaryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.OUT_EXPORT_PROCESSING_CHANNEL;

/**
 * @author Eugene Stepanenkov
 */
@Component
public class ExportItemWriter extends PollableChannelItemWriter<SinnerSummaryModel> {

    @Value("${flow.exportItemWriter.timeout}")
    private int timeout;

    @Autowired
    @Qualifier(OUT_EXPORT_PROCESSING_CHANNEL)
    private PollableChannel outProcessingChannel;

    @PostConstruct
    private void init() {

        setChannel(outProcessingChannel);
        setTimeout(timeout);
    }
}
