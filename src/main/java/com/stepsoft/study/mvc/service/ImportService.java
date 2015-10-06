package com.stepsoft.study.mvc.service;

import com.stepsoft.study.configuration.flow.ImportGateway;
import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eugene Stepanenkov
 */
@Service
public class ImportService {

    @Autowired
    private ImportGateway gateway;

    public Long add(SinnerModel sinnerModel) {

        SinnerModel added = gateway.add(sinnerModel);
        return added.getId();
    }

    public void modify(SinnerModel sinnerModel) {

        gateway.modify(sinnerModel);
    }

    public void remove(Long id) {

        gateway.remove(id);
    }

    public SinnerModel find(Long id) {

        return gateway.find(id);
    }
}
