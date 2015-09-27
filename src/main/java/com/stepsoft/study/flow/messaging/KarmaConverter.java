package com.stepsoft.study.flow.messaging;

import com.stepsoft.study.data.entity.Karma;
import com.stepsoft.study.data.entity.Sinner;
import com.stepsoft.study.mvc.model.KarmaModel;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.integration.config.IntegrationConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.springframework.core.convert.TypeDescriptor.valueOf;

/**
 * @author Eugene Stepanenkov
 */
@Component
@IntegrationConverter
public class KarmaConverter implements GenericConverter {

    private KarmaModel convert(Karma karma) {

        KarmaModel karmaModel = null;

        if (karma != null) {

            karmaModel = new KarmaModel();
            karmaModel.setId(karma.getId());
            karmaModel.setDrunkBottles(karma.getDrunkBottles());
            karmaModel.setMaliciousLevel(karma.getMaliciousLevel());
            karmaModel.setSeenBlasphemy(karma.getSeenBlasphemy());

            if (karma.getSinner() != null) {
                karmaModel.setSinnerId(karma.getSinner().getId());
            }
        }

        return karmaModel;
    }

    private Karma convert(KarmaModel karmaModel) {

        Karma karma = null;

        if (karmaModel != null) {

            karma = new Karma();
            karma.setId(karmaModel.getId());
            karma.setDrunkBottles(karmaModel.getDrunkBottles());
            karma.setMaliciousLevel(karmaModel.getMaliciousLevel());
            karma.setSeenBlasphemy(karmaModel.getSeenBlasphemy());

            if (karmaModel.getSinnerId() != null) {

                Sinner sinner = new Sinner();
                sinner.setId(karmaModel.getSinnerId());
                karma.setSinner(sinner);
            }
        }

        return karma;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {

        return new HashSet<>(asList(
                new ConvertiblePair(Karma.class, KarmaModel.class),
                new ConvertiblePair(KarmaModel.class, Karma.class)));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (sourceType.isAssignableTo(valueOf(Karma.class))) {
            return convert(((Karma) source));
        } else if (sourceType.isAssignableTo(valueOf(KarmaModel.class))) {
            return convert(((KarmaModel) source));
        }

        throw new IllegalStateException();
    }
}
