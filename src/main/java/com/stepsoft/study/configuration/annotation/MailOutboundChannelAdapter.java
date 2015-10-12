package com.stepsoft.study.configuration.annotation;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Eugene Stepanenkov
 */
@Target({METHOD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@ServiceActivator
public @interface MailOutboundChannelAdapter {

    String inputChannel() default "";

    String outputChannel() default "";

    String requiresReply() default "";

    String[] adviceChain() default {};

    String sendTimeout() default "";

    String autoStartup() default "";

    String phase() default "";

    Poller[] poller() default {};
}
