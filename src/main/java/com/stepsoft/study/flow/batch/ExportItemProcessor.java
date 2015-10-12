package com.stepsoft.study.flow.batch;

import com.stepsoft.study.data.entity.Karma;
import com.stepsoft.study.data.entity.Sinner;
import com.stepsoft.study.mvc.model.SinnerSummaryModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Eugene Stepanenkov
 */
@Component
public class ExportItemProcessor implements ItemProcessor<Sinner, SinnerSummaryModel> {

    private void process(Set<Karma> karmas, SinnerSummaryModel result) {

        long drunkBottles = 0;
        int maliciousLevelSummary = 0;
        int seenBlasphemy = 0;

        for (Karma karma : karmas) {

            drunkBottles += karma.getDrunkBottles();
            maliciousLevelSummary += karma.getMaliciousLevel();
            seenBlasphemy += karma.getSeenBlasphemy() ? 1 : 0;
        }

        result.setDrunkBottles(drunkBottles);
        result.setMaliciousLevel(maliciousLevelSummary / karmas.size());
        result.setSeenBlasphemy(seenBlasphemy);
    }

    @Override
    public SinnerSummaryModel process(Sinner sinner) throws Exception {

        SinnerSummaryModel result = new SinnerSummaryModel();
        result.setSinnerId(sinner.getId());
        result.setSinnerUserName(sinner.getUserName());

        process(sinner.getKarmas(), result);

        return result;

    }

}
