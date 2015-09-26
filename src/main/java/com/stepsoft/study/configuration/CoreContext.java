package com.stepsoft.study.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stepsoft.study.configuration.mvc.CoreWebContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@Import(CoreWebContext.class)
public class CoreContext {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
