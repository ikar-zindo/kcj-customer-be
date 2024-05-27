--
-- Dumping data for tables
--

--
-- Dumping data for table restaurants
--

INSERT INTO `kcj`.`restaurants` (`name`, `address`, `phone_number`, `opening_hours`, `cuisine_type`, `description`, `social_media_links`) VALUES
    ('K-Curry Jib', 'Alexanderplaz 1, 10178', '+49 123 456 789', '12:00 - 22:00', 'Asian food', '', 'social-media-link.com');

--
-- Dumping data for table customers
--

INSERT INTO `kcj`.`customers` (`customer_id`, `first_name`, `last_name`, `email`, `password`, `username`, `phone_number`, `address`, `postal_code`, `role`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'), 'Maria', 'Anders',	'maria@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', 'maria', '+49 123 456 789', 'Obere Str. 57', '12209', 'ROLE_CUSTOMER');

--
-- Dumping data for table carts
--

INSERT INTO `kcj`.`carts` (`cart_id`, `customer_id`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b1'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'));

--
-- Dumping data for table employees
--

INSERT INTO `kcj`.`employees` (`employee_id`, `last_name`, `first_name`,  `email`, `nickname`, `password`, `phone_number`, `role`, `restaurant_id`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c1'), 'Davolio', 'Nancy', 'davolio@mail.com', 'nancy', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_USER', 1);

--
-- Dumping data for table products
--

INSERT INTO `kcj`.`products` (`name`, `description`, `price`, `restaurant_id`, `image_url`) VALUES
    ('Chicken Curry', 'Fried chicken with vegetables and rice in medium spicy curry sauce', 14, 1, '1.jpg');

--
-- Dumping data for table orders
--

INSERT INTO `kcj`.`orders` (`order_id`, `customer_id`, `restaurant_id`, `employee_id`, `delivery_address`, `postal_code`, `total_amount`, `order_status`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49d1'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'), '1', UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c1'), 'Taczaka 2', '61891', '26', 'CREATED');

--
-- Dumping data for table order_product
--

INSERT INTO `kcj`.`order_products` (`order_product_id`, `order_id`, `product_id`, `quantity`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f41d1'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49d1'), '1', '2');

--
-- Dumping data for table `review`
--

INSERT INTO `kcj`.`reviews` (`restaurant_id`, `customer_id`, `rating`, `comment`) VALUES
    ('1', UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'), '5', 'good food');