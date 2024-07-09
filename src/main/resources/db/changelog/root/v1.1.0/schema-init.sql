-- liquibase formatted sql
-- changeset root:v1.1.0-schema-init
-- comment initial schema db

SET AUTOCOMMIT = 0;
START TRANSACTION;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: k-curry-jib
--

-- --------------------------------------------------------

--
-- Table structure for table customers
--

CREATE TABLE IF NOT EXISTS `customers`
(
    `customer_id`  BINARY(16) PRIMARY KEY,
    `first_name`   VARCHAR(30),
    `last_name`    VARCHAR(30),
    `email`        VARCHAR(60),
    `password`     VARCHAR(60),
    `phone_number` VARCHAR(15),
    `address`      VARCHAR(120),
    `postal_code`  VARCHAR(5),
    `created_at`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   TIMESTAMP,
    `role`         ENUM('ROLE_CUSTOMER') DEFAULT 'ROLE_CUSTOMER',
    `is_blocked`   BOOL      DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table carts
--

CREATE TABLE IF NOT EXISTS `carts`
(
    `cart_id`     BINARY(16) PRIMARY KEY,
    `customer_id` BINARY(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_unicode_ci;

--
-- Table structure for table cart_products
--

CREATE TABLE IF NOT EXISTS `cart_products`
(
    `cart_product_id` BINARY(16) PRIMARY KEY,
    `cart_id`         BINARY(16) NOT NULL,
    `product_id`      BIGINT,
    `quantity`        INT,
    `created_at`      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table orders
--

CREATE TABLE IF NOT EXISTS `orders`
(
    `order_id`         BINARY(16) PRIMARY KEY,
    `customer_id`      BINARY(16) NOT NULL,
    `restaurant_id`    BIGINT,
    `employee_id`      BINARY(16),
    `created_at`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `update_at`        TIMESTAMP,
    `delivery_address` VARCHAR(60),
    `postal_code`      VARCHAR(5),
    `total_amount`     DECIMAL(8, 2),
    `order_status`     ENUM('CREATED', 'COOKING', 'DELIVERING', 'COMPLETED', 'CANCELLED')
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table order_products
--

CREATE TABLE IF NOT EXISTS `order_products`
(
    `order_product_id` BINARY(16) PRIMARY KEY,
    `order_id`         BINARY(16) NOT NULL,
    `product_id`       BIGINT,
    `quantity`         INT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table products
--

CREATE TABLE IF NOT EXISTS `products`
(
    `product_id`    BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`          VARCHAR(60),
    `description`   TEXT,
    `price`         DECIMAL(8, 2),
    `restaurant_id` BIGINT,
    `image_url`     VARCHAR(200),
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `is_available`  BOOL      DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table restaurants
--

CREATE TABLE IF NOT EXISTS `restaurants`
(
    `restaurant_id`      BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`               VARCHAR(60),
    `address`            VARCHAR(120),
    `phone_number`       VARCHAR(20),
    `opening_hours`      VARCHAR(20),
    `cuisine_type`       VARCHAR(20),
    `description`        TEXT,
    `social_media_links` VARCHAR(200),
    `is_open`            BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table reviews
--

CREATE TABLE IF NOT EXISTS `reviews`
(
    `review_id`     BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `restaurant_id` BIGINT,
    `customer_id`   BINARY(16) NOT NULL,
    `rating`        DECIMAL(5, 2),
    `comment`       TEXT,
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table employees
--

CREATE TABLE IF NOT EXISTS `employees`
(
    `employee_id`   BINARY(16) PRIMARY KEY,
    `first_name`    VARCHAR(30),
    `last_name`     VARCHAR(30),
    `email`         VARCHAR(120),
    `username`      VARCHAR(60),
    `password`      VARCHAR(60),
    `phone_number`  VARCHAR(20),
    `role` ENUM('ROLE_USER', 'ROLE_MANAGER', 'ROLE_ADMIN', 'ROLE_DEALER', 'ROLE_DRIVER'),
    `restaurant_id` BIGINT,
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `is_active`     BOOL      DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Indexes for dumped tables
--

--
-- Indexes for table carts
--

ALTER TABLE `carts`
    ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table cart_products
--

ALTER TABLE `cart_products`
    ADD KEY `product_id` (`product_id`);

--
-- Indexes for table orders
--

ALTER TABLE `orders`
    ADD KEY `customer_id` (`customer_id`),
    ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table order_products
--

ALTER TABLE `order_products`
    ADD KEY `order_id` (`order_id`),
    ADD KEY `product_id` (`product_id`);

--
-- Indexes for table products
--

ALTER TABLE `products`
    ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table reviews
--

ALTER TABLE `reviews`
    ADD KEY `restaurant_id` (`restaurant_id`),
    ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table employees
--

ALTER TABLE `employees`
    ADD KEY `employee_id` (`employee_id`);

-- --------------------------------------------------------

--
-- Constraints for dumped tables
--

--
-- Constraints for table customers
--

ALTER TABLE `customers`
    ADD CONSTRAINT unique_customer_email UNIQUE (email);

--
-- Constraints for table carts
--

ALTER TABLE `carts`
    ADD CONSTRAINT `carts_fk_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`);

--
-- Constraints for table cart_products
--

ALTER TABLE `cart_products`
    ADD CONSTRAINT `cart_products_fk_carts` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`),
	ADD CONSTRAINT `cart_products_fk_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table orders
--

ALTER TABLE `orders`
    ADD CONSTRAINT `orders_fk_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  ADD CONSTRAINT `orders_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`restaurant_id`),
  ADD CONSTRAINT `orders_fk_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);

--
-- Constraints for table order_products
--

ALTER TABLE `order_products`
    ADD CONSTRAINT `order_products_fk_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
	ADD CONSTRAINT `order_products_fk_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table products
--

ALTER TABLE `products`
    ADD CONSTRAINT `products_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES restaurants (`restaurant_id`);

--
-- Constraints for table reviews
--

ALTER TABLE `reviews`
    ADD CONSTRAINT `review_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES restaurants (`restaurant_id`),
	ADD CONSTRAINT `reviews_fk_customers` FOREIGN KEY (`customer_id`) REFERENCES customers (`customer_id`);

--
-- Constraints for table employees
--

ALTER TABLE `employees`
    ADD CONSTRAINT `employees_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES restaurants (`restaurant_id`),
    ADD CONSTRAINT unique_username UNIQUE (username);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;