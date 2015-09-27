package com.stepsoft.study.mvc.controller;

import com.stepsoft.study.configuration.flow.ImportGateway;
import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ImportGateway importGateway;

    @Override
    protected Long add(SinnerModel sinnerModel) {

        return importGateway.add(sinnerModel);
    }

    @Override
    protected Set<Long> add(Set<SinnerModel> sinnerModels) {

        return importGateway.add(sinnerModels);
    }

    @Override
    protected void modify(SinnerModel sinnerModel) {

        importGateway.modify(sinnerModel);
    }

    @Override
    protected void remove(Long id) {

        importGateway.remove(id);
    }

    @Override
    protected SinnerModel find(Long id) {

        return importGateway.find(id);
    }
}
