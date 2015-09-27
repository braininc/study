package com.stepsoft.study.configuration.utils;

/**
 * @author Eugene Stepanenkov
 */
public abstract class ConfigurationConstants {

    // Channel names
    public static final String ADDING_MODEL_CHANNEL = "addingModelChannel";
    public static final String ADDING_MODEL_BULK_CHANNEL = "addingModelBulkChannel";
    public static final String MODIFYING_MODEL_CHANNEL = "modifyingModelChannel";
    public static final String REMOVING_MODEL_CHANNEL = "removingModelChannel";
    public static final String FINDING_MODEL_CHANNEL = "findingModelChannel";
    public static final String ADDING_MODEL_REPLY_CHANNEL = "addingModelReplyChannel";
    public static final String ADDING_MODEL_BULK_REPLY_CHANNEL = "addingModelBulkReplyChannel";
    public static final String FINDING_MODEL_REPLY_CHANNEL = "findingModelReplyChannel";
    public static final String REPLY_DB_CHANNEL = "replyDbChannel";
    public static final String IN_MODEL_GATEWAY_CHANNEL = "inModelGatewayChannel";
    public static final String OUT_MODEL_GATEWAY_CHANNEL = "outModelGatewayChannel";

    // Message header keys
    public static final String BULK_SIZE = "BULK_SIZE";
    public static final String MODEL_GATEWAY_ACTION = "MODEL_GATEWAY_ACTION";

    // MVC paths, mappings, headers, parameters
    public static final String BULK = "BULK";
    public static final String SINNERS = "/sinners";

    // Others...
    public static final String TRUE = "true";
    public static final String HIBERNATE_DIALECT_PROPERTY_KEY = "hibernate.dialect";
    public static final String HIBERNATE_SHOW_SQL_PROPERTY_KEY = "hibernate.show_sql";

    private ConfigurationConstants() {
    }
}
