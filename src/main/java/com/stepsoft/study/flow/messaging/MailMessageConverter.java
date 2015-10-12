package com.stepsoft.study.flow.messaging;

import com.stepsoft.study.mvc.model.SinnerSummaryModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.integration.config.IntegrationConverter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Eugene Stepanenkov
 */
@Component
@IntegrationConverter
public class MailMessageConverter implements Converter<List<SinnerSummaryModel>, String> {

    @Override
    public String convert(List<SinnerSummaryModel> summaries) {

        final StringBuilder payload = new StringBuilder();
        payload.append("id\tusername\tdb\tml\tsb\n");

        for (SinnerSummaryModel summary : summaries) {
            payload.append(summary.getSinnerId()).append("\t");
            payload.append(summary.getSinnerUserName()).append("\t");
            payload.append(summary.getDrunkBottles()).append("\t");
            payload.append(summary.getMaliciousLevel()).append("\t");
            payload.append(summary.getSeenBlasphemy()).append("\t");
        }

        return payload.toString();
    }
}
