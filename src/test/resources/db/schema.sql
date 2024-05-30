--
-- Database: `kcj`
--

-- --------------------------------------------------------

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
);

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
    `is_available`  BOOL      DEFAULT TRUE,
    CONSTRAINT `products_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES restaurants (`restaurant_id`)
);

--
-- Table structure for table customers
--

CREATE TABLE IF NOT EXISTS `customers`
(
    `customer_id`  BINARY(16) PRIMARY KEY,
    `first_name`   VARCHAR(30),
    `last_name`    VARCHAR(30),
    `email`        VARCHAR(60) UNIQUE,
    `password`     VARCHAR(60),
    `phone_number` VARCHAR(15),
    `address`      VARCHAR(120),
    `postal_code`  VARCHAR(5),
    `created_at`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   TIMESTAMP,
    `role`         ENUM('ROLE_CUSTOMER'),
    `is_blocked`   BOOL      DEFAULT FALSE
);

--
-- Table structure for table carts
--

CREATE TABLE IF NOT EXISTS `carts`
(
    `cart_id`     BINARY(16) PRIMARY KEY,
    `customer_id` BINARY(16) NOT NULL,
    CONSTRAINT `carts_fk_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
);

--
-- Table structure for table cart_products
--

CREATE TABLE IF NOT EXISTS `cart_products`
(
    `cart_product_id` BINARY(16) PRIMARY KEY,
    `cart_id`         BINARY(16) NOT NULL,
    `product_id`      BIGINT,
    `quantity`        INT,
    `created_at`      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      TIMESTAMP,
    CONSTRAINT `cart_products_fk_carts` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`),
    CONSTRAINT `cart_products_fk_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
);

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
    `updated_at`    TIMESTAMP,
    CONSTRAINT `review_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES restaurants (`restaurant_id`),
    CONSTRAINT `reviews_fk_customers` FOREIGN KEY (`customer_id`) REFERENCES customers (`customer_id`)
);

--
-- Table structure for table employees
--

CREATE TABLE IF NOT EXISTS `employees`
(
    `employee_id`   BINARY(16) PRIMARY KEY,
    `first_name`    VARCHAR(30),
    `last_name`     VARCHAR(30),
    `email`         VARCHAR(120) UNIQUE,
    `nickname`      VARCHAR(120) UNIQUE,
    `password`      VARCHAR(60),
    `phone_number`  VARCHAR(20),
    `role`          ENUM('ROLE_USER', 'ROLE_MANAGER', 'ROLE_ADMIN', 'ROLE_DEALER', 'ROLE_DRIVER'),
    `restaurant_id` BIGINT,
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `is_active`     BOOL      DEFAULT TRUE,
    CONSTRAINT `employees_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES restaurants (`restaurant_id`)
);

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
    `order_status`     ENUM('CREATED', 'COOKING', 'DELIVERING', 'COMPLETED', 'CANCELLED'),
    CONSTRAINT `orders_fk_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
    CONSTRAINT `orders_fk_restaurants` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`restaurant_id`),
    CONSTRAINT `orders_fk_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
);

--
-- Table structure for table order_products
--

CREATE TABLE IF NOT EXISTS `order_products`
(
    `order_product_id` BINARY(16) PRIMARY KEY,
    `order_id`         BINARY(16) NOT NULL,
    `product_id`       BIGINT,
    `quantity`         INT,
    CONSTRAINT `order_products_fk_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `order_products_fk_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
);