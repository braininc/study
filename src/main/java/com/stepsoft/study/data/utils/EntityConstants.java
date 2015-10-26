package com.stepsoft.study.data.utils;

/**
 * @author Eugene Stepanenkov
 */
public abstract class EntityConstants {

    public static final String SINNER_ID = "sinner_id";
    public static final String SINNER = "sinner";
    public static final String USER_NAME = "user_name";
    public static final String DRUNK_BOTTLES = "drunk_bottles";
    public static final String MALICIOUS_LEVEL = "malicious_level";
    public static final String SEEN_BLASPHEMY = "seen_blasphemy";
    public static final String IS_PROCESSED = "is_processed";

    // JPA Queries
    public static final String FROM_SINNER_WHERE_IS_PROCESSED_FALSE =
            "from Sinner where isProcessed = false";

    // SQL Queries
    public static final String UPDATE_SINNER_SET_IS_PROCESSED_TRUE_WHERE_ID =
            "update sinner set is_processed=true where id = ?";

    private EntityConstants() {
    }
}
