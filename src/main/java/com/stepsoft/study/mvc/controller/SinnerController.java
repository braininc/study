package com.stepsoft.study.mvc.controller;

import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.SINNERS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Eugene Stepanenkov
 */
@Controller
@RequestMapping(value = SINNERS, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class SinnerController extends BaseController<SinnerModel> {

    @Override
    protected Long add(SinnerModel model) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Set<Long> add(Set<SinnerModel> models) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void modify(SinnerModel model) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void remove(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected SinnerModel find(Long id) {
        throw new UnsupportedOperationException();
    }
}
