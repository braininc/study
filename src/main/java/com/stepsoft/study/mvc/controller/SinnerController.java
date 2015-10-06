package com.stepsoft.study.mvc.controller;

import com.stepsoft.study.mvc.model.SinnerModel;
import com.stepsoft.study.mvc.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.SINNERS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Eugene Stepanenkov
 */
@Controller
@RequestMapping(value = SINNERS, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class SinnerController extends BaseController<SinnerModel> {

    @Autowired
    private ImportService importService;

    @Override
    protected Long add(SinnerModel sinnerModel) {

        return importService.add(sinnerModel);
    }

    @Override
    protected void modify(SinnerModel sinnerModel) {

        importService.modify(sinnerModel);
    }

    @Override
    protected void remove(Long id) {

        importService.remove(id);
    }

    @Override
    protected SinnerModel find(Long id) {

        return importService.find(id);
    }
}
