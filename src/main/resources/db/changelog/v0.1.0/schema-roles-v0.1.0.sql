--liquibase formatted sql

--changeset root:v0.1.0-schema-roles
--comment initial tables roles and authorities

SET AUTOCOMMIT = 0;
START TRANSACTION;

--
-- Table structure for table roles
--

CREATE TABLE IF NOT EXISTS `kcj`.`roles`
(
    `role_id`   BINARY(16) PRIMARY KEY,
    `role_name` ENUM ('ROLE_USER',
                      'ROLE_MANAGER',
                      'ROLE_ADMIN',
                      'ROLE_DEALER',
                      'ROLE_DRIVER')
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_unicode_ci;

--
-- Table structure for table authorities
--

CREATE TABLE IF NOT EXISTS `kcj`.`authorities`
(
    `auth_id`   BINARY(16) PRIMARY KEY,
    `authority` ENUM ('MANAGE_ORDERS',
                      'MANAGE_CUSTOMERS',
                      'MANAGE_EMPLOYEES')
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table role_authorities
--

CREATE TABLE IF NOT EXISTS `kcj`.`employee_roles`
(
    employee_id BINARY(16) NOT NULL,
    role_id      BINARY(16) NOT NULL,
    PRIMARY KEY (employee_id, role_id),
    FOREIGN KEY (employee_id) REFERENCES `employees`(employee_id),
    FOREIGN KEY (role_id) REFERENCES `roles`(role_id)
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table role_authorities
--

CREATE TABLE IF NOT EXISTS `kcj`.`role_authorities`
(
    role_id BINARY(16),
    auth_id BINARY(16),
    FOREIGN KEY (role_id) REFERENCES `roles`(role_id),
    FOREIGN KEY (auth_id) REFERENCES `authorities`(auth_id),
    PRIMARY KEY (role_id, auth_id)
    ) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_unicode_ci;

COMMIT;