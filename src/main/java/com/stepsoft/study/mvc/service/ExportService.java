package com.stepsoft.study.mvc.service;

import com.stepsoft.study.flow.messaging.ExportGateway;
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

    public void report() {
        gateway.report();
    }
}
