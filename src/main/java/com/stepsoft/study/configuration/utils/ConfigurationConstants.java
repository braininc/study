package com.stepsoft.study.configuration.utils;

/**
 * @author Eugene Stepanenkov
 */
public abstract class ConfigurationConstants {

    private ConfigurationConstants() {
    }

    // Bean names
    public static final String ADDING_MODEL_CHANNEL = "addingModelChannel";
    public static final String ADDING_MODEL_BULK_CHANNEL = "addingModelBulkChannel";
    public static final String MODIFYING_MODEL_CHANNEL = "modifyingModelChannel";
    public static final String REMOVING_MODEL_CHANNEL = "removingModelChannel";
    public static final String FINDING_MODEL_CHANNEL = "findingModelChannel";
    public static final String ADDING_MODEL_REPLY_CHANNEL = "addingModelReplyChannel";
    public static final String ADDING_MODEL_BULK_REPLY_CHANNEL = "addingModelBulkReplyChannel";
    public static final String FINDING_MODEL_REPLY_CHANNEL = "findingModelReplyChannel";
    public static final String REPLY_DB_CHANNEL = "replyDbChannel";
    public static final String IN_DB_CHANNEL = "inDbChannel";

    // Package locations
    public static final String MVC_CONTROLLER_PACKAGE = "com.stepsoft.study.mvc.controller";
    public static final String FLOW_PACKAGE = "com.stepsoft.study.flow";
    public static final String FLOW_CONFIGURATION_PACKAGE = "com.stepsoft.study.configuration.flow";

    // Message header keys
    public static final String BULK_SIZE = "BULK_SIZE";
    public static final String MODEL_GATE_WAY_ACTION = "MODEL_GATE_WAY_ACTION";

    // Others...
    public static final String TRUE = "true";
}
