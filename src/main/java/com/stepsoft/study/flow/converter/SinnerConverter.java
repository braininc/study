package com.stepsoft.study.flow.converter;

import java.util.HashSet;
import java.util.Set;

import com.stepsoft.study.data.entity.Karma;
import com.stepsoft.study.data.entity.Sinner;
import com.stepsoft.study.mvc.model.KarmaModel;
import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.integration.config.IntegrationConverter;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.core.convert.TypeDescriptor.valueOf;

/**
 * @author Eugene Stepanenkov
 */
@Component
@IntegrationConverter
public class SinnerConverter implements GenericConverter {

    @Autowired
    private KarmaConverter karmaConverter;

    private SinnerModel convert(Sinner sinner) {

        SinnerModel sinnerModel = null;

        if (sinner != null) {

            sinnerModel = new SinnerModel();
            sinnerModel.setId(sinner.getId());
            sinnerModel.setUserName(sinner.getUserName());

            if (sinner.getKarmas() != null) {

                Set<KarmaModel> karmaModels = sinner.getKarmas()
                        .stream()
                        .map(karma -> ((KarmaModel) karmaConverter
                                .convert(karma, valueOf(Karma.class), valueOf(KarmaModel.class))))
                        .collect(toSet());

                sinnerModel.setKarmas(karmaModels);
            }
        }

        return sinnerModel;
    }

    private Sinner convert(SinnerModel sinnerModel) {

        if (sinnerModel != null) {

            final Sinner sinner = new Sinner();
            sinner.setId(sinnerModel.getId());
            sinner.setUserName(sinnerModel.getUserName());

            if (sinnerModel.getKarmas() != null) {

                Set<Karma> karmas = sinnerModel.getKarmas()
                        .stream()
                        .map(karmaModel -> {

                            Karma karma = ((Karma) karmaConverter
                                    .convert(karmaModel, valueOf(KarmaModel.class), valueOf(Karma.class)));
                            karma.setSinner(sinner);

                            return karma;
                        })
                        .collect(toSet());

                sinner.setKarmas(karmas);

            }
            return sinner;
        }

        return null;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {

        return new HashSet<>(asList(
                new ConvertiblePair(Sinner.class, SinnerModel.class),
                new ConvertiblePair(SinnerModel.class, Sinner.class)));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        if (sourceType.isAssignableTo(valueOf(Sinner.class))) {
            return convert(((Sinner) source));
        } else if (sourceType.isAssignableTo(valueOf(SinnerModel.class))) {
            return convert(((SinnerModel) source));
        }

        throw new IllegalStateException();
    }
}
