package com.stepsoft.study.mvc.controller;

import com.stepsoft.study.mvc.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.stepsoft.study.configuration.utils.ConfigurationConstants.EXPORT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Eugene Stepanenkov
 */
@Controller
@RequestMapping(value = EXPORT)
public class ExportController {

    @Autowired
    private ExportService exportService;

    @RequestMapping(method = POST)
    public void post() {

        exportService.report();
    }
}
