SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+01:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE DATABASE IF NOT EXISTS `k-curry-jib` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `k-curry-jib`;

--
-- Database: `k-curry-jib`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  customer_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  cart_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_product` (
  cart_product_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cart_id BIGINT,
  product_id BIGINT,
  quantity INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  order_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT,
  restaurant_id BIGINT,
  employee_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_at TIMESTAMP,
  delivery_address VARCHAR(60),
  postal_code VARCHAR(5),
  total_amount DECIMAL(8, 2),
  order_status ENUM('CREATED', 'COOKING', 'DELIVERING', 'COMPLETED', 'CANCELLED')
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_product` (
  order_product_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT,
  product_id BIGINT,
  quantity INT
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  review_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  restaurant_id BIGINT,
  customer_id BIGINT,
  rating DECIMAL(5, 2),
  comment TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  employee_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

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
	
-- --------------------------------------------------------

--
-- Dumping data for tables
--

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`name`, `address`, `phone_number`, `opening_hours`, `cuisine_type`, `description`, `social_media_links`) VALUES
    ('K-Curry Jib', 'Alexanderplaz 1, 10178', '+49 123 456 789', '12:00 - 22:00', 'Asian food', '', 'social-media-link.com');

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`first_name`, `last_name`, `email`, `password`, `username`, `phone_number`, `address`, `postal_code`, `role`) VALUES
    ('Maria', 'Anders',	'maria@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', 'maria', '+49 123 456 789', 'Obere Str. 57', '12209', 'ROLE_CUSTOMER'),
    ('Ana', 'Trujillo',	'ana@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'ana', '+49 123 456 789', 'Minerstrasse 33', '10115', 'ROLE_CUSTOMER'),
    ('Antonio', 'Moreno', 'antonio@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', 'antonio', '+49 123 456 789', 'Alexanderplatz 3', '10178', 'ROLE_CUSTOMER'),
    ('Thomas', 'Hardy',	'thomas@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', 'thomas', '+49 123 456 789', 'Friedrichstrasse 123', '10117', 'ROLE_CUSTOMER'),
    ('Christina', 'Berglund', 'christina@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'christina', '+49 123 456 789', 'Karl-Liebknecht-Strasse 29', '10178', 'ROLE_CUSTOMER'),
    ('Hanna', 'Moos', 'hanna@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', 'hanna', '+49 123 456 789', 'Potsdamer Platz 1', '10785', 'ROLE_CUSTOMER'),
    ('Frederique', 'Citeaux', 'frederique@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'frederique', '+49 123 456 789', 'Stralauer Strasse 34', '10243', 'ROLE_CUSTOMER'),
    ('Martin', 'Sommer', 'martin@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', 'martin', '+49 123 456 789', 'Lichtenberger Strasse 11', '10179', 'ROLE_CUSTOMER'),
    ('Laurence', 'Lebihans', 'laurence@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'laurence', '+49 123 456 789', 'Charlottenstrasse 22', '10117', 'ROLE_CUSTOMER'),
    ('Elizabeth', 'Lincoln', 'elizabeth@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', 'elizabeth', '+49 123 456 789', 'Pariser Platz 7', '10117', 'ROLE_CUSTOMER');

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`customer_id`) VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10);

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`last_name`, `first_name`,  `email`, `nickname`, `password`, `phone_number`, `role`, `restaurant_id`) VALUES
    ('Davolio', 'Nancy', 'davolio@mail.com', 'nancy', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_USER', 1),
    ('Fuller', 'Ewa', 'fuller@mail.com', 'ewa', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 'ROLE_USER', 1),
    ('Leverling', 'Janet', 'leverling@mail.com', 'janet', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 'ROLE_USER', 1),
    ('Peacock', 'Margaret', 'peacock@mail.com', 'margaret', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 'ROLE_USER', 1),
    ('Buchanan', 'Steven', 'buchanan@mail.com', 'steven', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 'ROLE_USER', 1),
    ('Suyama', 'Michael', 'suyama@mail.com', 'manager', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_MANAGER', 1),
    ('King', 'Robert', 'king@mail.com', 'robert', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_MANAGER', 1),
    ('Callahan', 'Laura', 'callahan@mail.com', 'admin', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_ADMIN', 1),
    ('Dodsworth', 'Anne', 'dodsworth@mail.com', 'anne', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 'ROLE_ADMIN', 1),
    ('West', 'Adam', 'west@mail.com', 'adam', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 'ROLE_ADMIN', 1);

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`name`, `description`,  `price`, `restaurant_id`, `image_url`) VALUES
    ('Chicken Curry', 'Fried chicken with vegetables and rice in medium spicy curry sauce', 14, 1, '1.jpg'),
    ('Bibimbap', 'Beef with rice, vegetables, seaweed, fried egg and chilli paste', 12, 1, '1.jpg'),
    ('Samgyupsal', 'Bacon for grill, lettuce, garlic, soy bean Samjang paste and leek salad', 40, 1, '1.jpg'),
    ('KimChi', 'Korean fermented dish of seasoned vegetables, known for its tangy, spicy taste', 4, 1, '1.jpg'),
    ('KimChi Zige', 'Spicy soup with kimchi, bacon, tofu & vegetables', 13, 1, '1.jpg'),
    ('Ozingo Kampungi', 'Fried battered squids in spicy-and-sweet sauce and vegetables', 18, 1, '1.jpg'),
    ('Ramen', 'Soup with noodles, beef or pork, egg, katsuobushi, vegetables and leek', 10, 1, '1.jpg'),
    ('Haemul Ramen', 'Spicy noodle soup with seafood, vegetables and egg', 7, 1, '1.jpg'),
    ('Miso  Shiru', 'Soybean soup with tofu, seaweed', 5, 1, '1.jpg'),
    ('Haemul Bokkum', 'Fried seawood with vegetables', 15, 1, '1.jpg'),
    ('Ori Teriyaki', 'Grilled duck with vegetables in sweet teriyaki sauce', 18, 1, '1.jpg'),
    ('Kampungi', 'Medium spicy chicken breast with vegetables', 10, 1, '1.jpg'),
    ('Chiken Teriyaki', 'Fried chicken leg with grilled vegetables in sweet teriyaki sauce', 8, 1, '1.jpg'),
    ('Yaki Tori', 'Chicken shashliks (2 pieces) in sweet sauce, fries', 6, 1, '1.jpg'),
    ('Yaki Gyoza', 'Fried dumplings with vegetable filling', 8, 1, '1.jpg'),
    ('Ebi Tempura', 'Fried shrimps coated in tempura, fries', 16, 1, '1.jpg'),
    ('Dubu Yache Bokkum', 'Fried tofu with vegetables in soya sauce', 9, 1, '1.jpg'),
    ('Yukhoe', 'Beef tartar, garlic, onion, sesame oil', 20, 1, '1.jpg'),
    ('Bulgogi Zungshik', 'Beef & vehetables in sweet sauce on the hot plate, rice', 13, 1, '1.jpg'),
    ('Osam Bokum', 'Fried squids, pork and vegetables in spicy gochujang sauce', 28, 1, '1.jpg');
	
--
-- Dumping data for table `order`
--
	
INSERT INTO `order` (`customer_id`, `restaurant_id`, `employee_id`, `delivery_address`, `postal_code`, `total_amount`, `order_status`, `update_at`) VALUES
	('1', '1', '1', 'Taczaka 2', '61891', '26', 'CREATED', '2024-01-24 23:55:41'),
	('2', '1', '1', 'Limanowskiego 21', '61540', '14', 'CREATED', '2024-01-24 23:55:41');

--
-- Dumping data for table `order_product`
--

INSERT INTO `order_product` (`order_id`, `product_id`, `quantity`) VALUES
	('1', '1', '1'),
    ('1', '2', '1'),
    ('2', '1', '1');

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`restaurant_id`, `customer_id`, `rating`, `comment`) VALUES
    ('1', '1', '5', 'good food'),
    ('1', '2', '4', 'nice'),
    ('1', '3', '2', 'not tasty, I didnt like the food');

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;