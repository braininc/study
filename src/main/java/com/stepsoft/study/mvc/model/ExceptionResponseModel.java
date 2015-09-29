package com.stepsoft.study.mvc.model;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Eugene Stepanenkov
 */
public class ExceptionResponseModel {

    private String message;
    private String type;
    private String traceMessage;

    public ExceptionResponseModel() {
    }

    public ExceptionResponseModel(Exception exception) {

        this(exception, false);
    }

    public ExceptionResponseModel(Exception exception, boolean exceptionWithStakeTrace) {

        this.message = exception.getMessage();
        this.type = exception.getClass().getTypeName();
        if (exceptionWithStakeTrace) {
            this.traceMessage = fetchStackTraceMessage(exception);
        }
    }

    private String fetchStackTraceMessage(Exception exception) {

        StringWriter stackTraceWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTraceWriter));

        return stackTraceWriter.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTraceMessage() {
        return traceMessage;
    }

    public void setTraceMessage(String traceMessage) {
        this.traceMessage = traceMessage;
    }
}
