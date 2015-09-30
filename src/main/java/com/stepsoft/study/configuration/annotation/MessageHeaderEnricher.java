package com.stepsoft.study.configuration.annotation;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Eugene Stepanenkov
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transformer
public @interface MessageHeaderEnricher {

    String inputChannel() default "";

    String outputChannel() default "";

    String[] adviceChain() default {};

    String sendTimeout() default "";

    String autoStartup() default "";

    String phase() default "";

    Poller[] poller() default {};
}
