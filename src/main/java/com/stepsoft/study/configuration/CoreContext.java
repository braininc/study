package com.stepsoft.study.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stepsoft.study.configuration.flow.FlowContext;
import com.stepsoft.study.configuration.mvc.CoreWebContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.MAIL_SMTP_AUTH;
import static com.stepsoft.study.configuration.utils.ConfigurationConstants.MAIL_SMTP_STARTTLS_ENABLED;

/**
 * @author Eugene Stepanenkov
 */
@Configuration
@PropertySource("classpath:app.properties")
@PropertySource("classpath:mail.properties")
@Import({
        CoreWebContext.class,
        FlowContext.class,
        CoreDataContext.class
})
public class CoreContext {

    @Value("${mail.server.port}")
    private int mailPort;

    @Value("${mail.server.host}")
    private String mailHost;

    @Value("${mail.server.username}")
    private String mailUserName;

    @Value("${mail.server.password}")
    private String mailPassword;

    @Value("${mail.server.auth}")
    private Boolean mailAuth;

    @Value("${mail.server.startTlsEnabled}")
    private Boolean mailStartTlsEnabled;

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

    @Bean
    public Properties mailProperties() {

        Properties properties = new Properties();
        properties.setProperty(MAIL_SMTP_AUTH, mailAuth.toString());
        properties.setProperty(MAIL_SMTP_STARTTLS_ENABLED, mailStartTlsEnabled.toString());

        return properties;
    }

    @Bean
    public JavaMailSender mailSender() {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailHost);
        sender.setPort(mailPort);
        sender.setUsername(mailUserName);
        sender.setPassword(mailPassword);
        sender.setJavaMailProperties(mailProperties());

        return sender;
    }
}
