package com.stepsoft.study.mvc;

import com.stepsoft.study.configuration.CoreContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author Eugene Stepanenkov
 */
public class MainApplicationInitializer implements WebApplicationInitializer {

    private static final String SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(CoreContext.class);

        ServletRegistration.Dynamic registration =
                servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(webContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}