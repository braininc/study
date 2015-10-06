package com.stepsoft.study.mvc.service;

import com.stepsoft.study.configuration.flow.ExportGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Eugene Stepanenkov
 */
@Service
public class ExportService {

    @Autowired
    private ExportGateway gateway;

    public void export() {
        gateway.export();
    }
}
