package com.stepsoft.study.mvc.controller;

import com.stepsoft.study.mvc.model.Sinner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Eugene Stepanenkov
 */
@Controller
@RequestMapping(value = SinnerController.SINNERS, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class SinnerController extends BaseController<Sinner> {

    public static final String SINNERS = "/sinners";

    @Override
    protected Long add(Sinner model) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void modify(Sinner model) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void remove(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Sinner find(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Set<Long> add(Set<Sinner> models) {
        throw new UnsupportedOperationException();
    }
}
