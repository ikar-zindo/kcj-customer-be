-- liquibase formatted sql
-- changeset customer:v1.3.0-deactivated-tokens
-- comment add deactivated-tokens

CREATE TABLE IF NOT EXISTS `deactivated_tokens`
(
    `token_id`      BINARY(16) PRIMARY KEY,
    `keep_until`    TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_unicode_ci;