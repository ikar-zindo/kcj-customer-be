-- liquibase formatted sql
-- changeset customer:v1.3.0-schema-logback
-- comment logback logging schema

SET
AUTOCOMMIT = 0;
START TRANSACTION;

CREATE TABLE logging_event
(
    timestmp          BIGINT       NOT NULL,
    formatted_message TEXT         NOT NULL,
    logger_name       VARCHAR(255) NOT NULL,
    level_string      VARCHAR(10)  NOT NULL,
    thread_name       VARCHAR(255),
    reference_flag    SMALLINT,
    arg0              VARCHAR(255),
    arg1              VARCHAR(255),
    arg2              VARCHAR(255),
    arg3              VARCHAR(255),
    caller_filename   VARCHAR(255) NOT NULL,
    caller_class      VARCHAR(255) NOT NULL,
    caller_method     VARCHAR(255) NOT NULL,
    caller_line       CHAR(4)      NOT NULL,
    event_id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE logging_event_property
(
    event_id     BIGINT       NOT NULL,
    mapped_key   VARCHAR(254) NOT NULL,
    mapped_value TEXT,
    PRIMARY KEY (event_id, mapped_key),
    FOREIGN KEY (event_id) REFERENCES logging_event (event_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE logging_event_exception
(
    event_id   BIGINT       NOT NULL,
    i          SMALLINT     NOT NULL,
    trace_line VARCHAR(255) NOT NULL,
    PRIMARY KEY (event_id, i),
    FOREIGN KEY (event_id) REFERENCES logging_event (event_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_unicode_ci;

COMMIT;