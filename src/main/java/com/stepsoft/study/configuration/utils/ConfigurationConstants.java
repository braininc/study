package com.stepsoft.study.configuration.utils;

/**
 * @author Eugene Stepanenkov
 */
public abstract class ConfigurationConstants {

    // Input Channel names
    public static final String IN_IMPORT_CHANNEL = "inImportChannel";
    public static final String IN_IMPORT_TRANSFORMER_CHANNEL = "inImportTransformerChannel";
    public static final String IN_IMPORT_DB_CHANNEL = "inImportDbChannel";
    public static final String IN_IMPORT_SPLITTER_CHANNEL = "inImportSplitterChannel";
    public static final String IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL = "inImportAddOrUpdateDbChannel";
    public static final String IN_IMPORT_FETCH_DB_CHANNEL = "inImportFetchDbChannel";
    public static final String IN_IMPORT_DELETE_DB_CHANNEL = "inImportDeleteDbChannel";
    public static final String IN_IMPORT_PROCESSING_CHANNEL = "inImportProcessingChannel";
    public static final String OUT_IMPORT_CHANNEL = "outImportChannel";
    public static final String OUT_IMPORT_DB_CHANNEL = "outImportDbChannel";
    public static final String OUT_IMPORT_TRANSFORMER_CHANNEL = "outImportTransformerChannel";
    public static final String OUT_IMPORT_AGGREGATOR_CHANNEL = "outImportAggregatorChannel";
    public static final String OUT_IMPORT_PROCESSING_CHANNEL = "outImportProcessingChannel";

    // Message header keys
    public static final String BULK_SIZE = "BULK_SIZE";
    public static final String IMPORT_ACTION = "IMPORT_ACTION";

    // MVC paths, mappings, headers, parameters
    public static final String BULK = "BULK";
    public static final String SINNERS = "/sinners";

    // Others...
    public static final String HIBERNATE_DIALECT_PROPERTY_KEY = "hibernate.dialect";
    public static final String HIBERNATE_SHOW_SQL_PROPERTY_KEY = "hibernate.show_sql";

    private ConfigurationConstants() {
    }
}
