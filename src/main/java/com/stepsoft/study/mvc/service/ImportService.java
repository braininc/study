package com.stepsoft.study.mvc.service;

import com.stepsoft.study.configuration.flow.ImportGateway;
import com.stepsoft.study.mvc.model.SinnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Eugene Stepanenkov
 */
@Service
public class ImportService {

    @Autowired
    private ImportGateway gateway;

    @Transactional
    public Long add(SinnerModel sinnerModel) {

        return gateway.add(sinnerModel);
    }

    @Transactional
    public Set<Long> add(Set<SinnerModel> sinnerModels) {

        return gateway.add(sinnerModels);
    }

    @Transactional
    public void modify(SinnerModel sinnerModel) {

        gateway.modify(sinnerModel);
    }

    @Transactional
    public void remove(Long id) {

        gateway.remove(id);
    }

    @Transactional
    public SinnerModel find(Long id) {

        return gateway.find(id);
    }
}
