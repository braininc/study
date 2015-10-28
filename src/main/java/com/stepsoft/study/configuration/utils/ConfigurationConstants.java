package com.stepsoft.study.configuration.utils;

/**
 * @author Eugene Stepanenkov
 */
public abstract class ConfigurationConstants {

    // Input Channel names
    public static final String IN_IMPORT_CHANNEL = "inImportChannel";
    public static final String IN_IMPORT_TRANSFORMATION_CHANNEL = "inImportTransformationChannel";
    public static final String IN_IMPORT_DB_CHANNEL = "inImportDbChannel";
    public static final String IN_IMPORT_ADD_OR_UPDATE_DB_CHANNEL = "inImportAddOrUpdateDbChannel";
    public static final String IN_IMPORT_FETCH_DB_CHANNEL = "inImportFetchDbChannel";
    public static final String IN_IMPORT_DELETE_DB_CHANNEL = "inImportDeleteDbChannel";
    public static final String IN_IMPORT_PRE_PERSIST_CHANNEL = "inImportPrePersistChannel";
    public static final String IN_IMPORT_POST_PERSIST_CHANNEL = "inImportPostPersistChannel";
    public static final String OUT_IMPORT_CHANNEL = "outImportChannel";
    public static final String OUT_IMPORT_DB_CHANNEL = "outImportDbChannel";
    public static final String OUT_IMPORT_TRANSFORMATION_CHANNEL = "outImportTransformerChannel";

    public static final String IN_EXPORT_CHANNEL = "inExportChannel";
    public static final String IN_EXPORT_TRANSFORMATION_CHANNEL = "inExportTransformationChannel";
    public static final String IN_EXPORT_PROCESSING_CHANNEL = "inExportProcessingChannel";
    public static final String IN_EXPORT_PROCESSING_LAUNCH_CHANNEL = "inExportProcessingLaunchChannel";
    public static final String IN_EXPORT_PROCESSING_READER_CHANNEL = "inExportProcessingReaderChannel";
    public static final String IN_EXPORT_SPLITTER_CHANNEL = "inExportSplitterChannel";
    public static final String OUT_EXPORT_PROCESSING_CHANNEL = "outExportProcessingChannel";
    public static final String OUT_EXPORT_NOTIFICATION_HEADER_ENRICHER_CHANNEL = "outExportHeaderEnricherChannel";
    public static final String OUT_EXPORT_NOTIFICATION_CHANNEL = "outExportNotificationChannel";

    // Bean names
    public static final String FLOW_METRICS_FACTORY = "flowMetricsFactory";
    public static final String IN_EXPORT_POLLER = "inExportPoller";
    public static final String EXPORT_JOB = "exportJob";
    public static final String EXPORT_STEP = "exportStep";

    // Message header keys
    public static final String IS_PERSISTED = "isPersisted";
    public static final String IMPORT_ACTION = "importAction";
    public static final String EXPORT_ACTION = "exportAction";

    // MVC paths, mappings, headers, parameters
    public static final String SINNERS = "/sinners";
    public static final String EXPORT = "/export";

    // Others
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLED = "mail.smtp.starttls.enable";

    private ConfigurationConstants() {
    }
}
