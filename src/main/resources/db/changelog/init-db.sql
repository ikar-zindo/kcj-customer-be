SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+01:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE SCHEMA IF NOT EXISTS `kcj-db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `kcj-db`;

--
-- Database: `k-curry-jib`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  customer_id BINARY(16) NOT NULL PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email VARCHAR(60),
  username VARCHAR(60),
  password VARCHAR(60),
  phone_number VARCHAR(15),
  address VARCHAR(120),
  postal_code VARCHAR(5),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  role ENUM('ROLE_CUSTOMER'),
  is_blocked BOOL DEFAULT FALSE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  cart_id BINARY(16) NOT NULL PRIMARY KEY,
  customer_id BINARY(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_product` (
  cart_product_id BINARY(16) NOT NULL PRIMARY KEY,
  cart_id BINARY(16) NOT NULL,
  product_id BIGINT,
  quantity INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  order_id BINARY(16) NOT NULL PRIMARY KEY,
  customer_id BINARY(16) NOT NULL,
  restaurant_id BIGINT,
  employee_id BINARY(16),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_at TIMESTAMP,
  delivery_address VARCHAR(60),
  postal_code VARCHAR(5),
  total_amount DECIMAL(8, 2),
  order_status ENUM('CREATED', 'COOKING', 'DELIVERING', 'COMPLETED', 'CANCELLED')
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_product` (
  order_product_id BINARY(16) NOT NULL PRIMARY KEY,
  order_id BINARY(16) NOT NULL,
  product_id BIGINT,
  quantity INT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  product_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60),
  description TEXT,
  price DECIMAL(8, 2),
  restaurant_id BIGINT,
  image_url VARCHAR(200),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_available BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  restaurant_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(60),
  address VARCHAR(120),
  phone_number VARCHAR(20),
  opening_hours VARCHAR(20),
  cuisine_type VARCHAR(20),
  description TEXT,
  social_media_links VARCHAR(200),
  is_open BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  review_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  restaurant_id BIGINT,
  customer_id BINARY(16) NOT NULL,
  rating DECIMAL(5, 2),
  comment TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  employee_id BINARY(16) NOT NULL PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email VARCHAR(120),
  nickname VARCHAR(60),
  password VARCHAR(60),
  phone_number VARCHAR(20),
  role ENUM('ROLE_USER', 'ROLE_MANAGER', 'ROLE_ADMIN', 'ROLE_DEALER', 'ROLE_DRIVER'),
  restaurant_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_active BOOL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE = utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--

ALTER TABLE `cart`
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `cart-product`
--

ALTER TABLE `cart_product`
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `order`
--

ALTER TABLE `order`
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table `order_detail`
--

ALTER TABLE `order_product`
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--

ALTER TABLE `product`
  ADD KEY `restaurant_id` (`restaurant_id`);

--
-- Indexes for table `review`
--

ALTER TABLE `review`
  ADD KEY `restaurant_id` (`restaurant_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `employee`
--

ALTER TABLE `employee`
  ADD KEY `employee_id` (`employee_id`);

-- --------------------------------------------------------

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--

ALTER TABLE `customer`
    ADD CONSTRAINT unique_nickname UNIQUE (email);

--
-- Constraints for table `cart`
--

ALTER TABLE `cart`
  ADD CONSTRAINT `cart_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `cart_product`
--

ALTER TABLE `cart_product`
	ADD CONSTRAINT `cart_product_fk_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
	ADD CONSTRAINT `cart_product_fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `order`
--

ALTER TABLE `order`
  ADD CONSTRAINT `order_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `order_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  ADD CONSTRAINT `order_fk_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `order_details`
--

ALTER TABLE `order_product`
	ADD CONSTRAINT `order_product_fk_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
	ADD CONSTRAINT `order_product_fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Constraints for table `product`
--

ALTER TABLE `product`
	ADD CONSTRAINT `product_fk_category` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
	ADD CONSTRAINT `product_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`);

--
-- Constraints for table `review`
--

ALTER TABLE `review`
	ADD CONSTRAINT `review_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
	ADD CONSTRAINT `review_fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `employee`
--

ALTER TABLE `employee`
    ADD CONSTRAINT `employee_fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
    ADD CONSTRAINT unique_nickname UNIQUE (nickname);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;