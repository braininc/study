package com.stepsoft.study.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stepsoft.study.configuration.flow.CoreDataContext;
import com.stepsoft.study.configuration.flow.FlowContext;
import com.stepsoft.study.configuration.mvc.CoreWebContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@PropertySource("classpath:app.properties")
@Import({
        CoreWebContext.class,
        FlowContext.class,
        CoreDataContext.class
})
public class CoreContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(NON_NULL);
        return mapper;
    }

    @Bean
    public ExpressionParser expressionParser() {

        return new SpelExpressionParser();
    }
}
