package com.stepsoft.study.mvc.controller;

import com.stepsoft.study.mvc.model.ExceptionResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.handler.ReplyRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Eugene Stepanenkov
 */
@ControllerAdvice(assignableTypes = BaseController.class)
public class ExceptionHandlingControllerAdvice {

    @Value("${app.mvc.response.exceptionWithStakeTrace}")
    private boolean exceptionWithStakeTrace;

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    @ResponseBody
    public ExceptionResponseModel handle405(UnsupportedOperationException exception) {

        return new ExceptionResponseModel(exception, exceptionWithStakeTrace);
    }

    @ExceptionHandler(ReplyRequiredException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ExceptionResponseModel handle404(ReplyRequiredException exception) {

        return new ExceptionResponseModel(exception, exceptionWithStakeTrace);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponseModel handle500(Exception exception) {

        return new ExceptionResponseModel(exception, exceptionWithStakeTrace);
    }
}
