package com.stepsoft.study.configuration.annotation;

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
@ServiceActivator(requiresReply = "true")
public @interface JpaOutboundGateway {


}
