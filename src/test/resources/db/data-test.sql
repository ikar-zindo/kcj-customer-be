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
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'), 'Maria', 'Anders',	'maria@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', 'maria', '+49 123 456 789', 'Obere Str. 57', '12209', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a2'), 'Ana', 'Trujillo',	'ana@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'ana', '+49 123 456 789', 'Minerstrasse 33', '10115', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a3'), 'Antonio', 'Moreno', 'antonio@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', 'antonio', '+49 123 456 789', 'Alexanderplatz 3', '10178', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a4'), 'Thomas', 'Hardy',	'thomas@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', 'thomas', '+49 123 456 789', 'Friedrichstrasse 123', '10117', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a5'), 'Christina', 'Berglund', 'christina@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'christina', '+49 123 456 789', 'Karl-Liebknecht-Strasse 29', '10178', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a6'), 'Hanna', 'Moos', 'hanna@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', 'hanna', '+49 123 456 789', 'Potsdamer Platz 1', '10785', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a7'), 'Frederique', 'Citeaux', 'frederique@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'frederique', '+49 123 456 789', 'Stralauer Strasse 34', '10243', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a8'), 'Martin', 'Sommer', 'martin@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', 'martin', '+49 123 456 789', 'Lichtenberger Strasse 11', '10179', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a9'), 'Laurence', 'Lebihans', 'laurence@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', 'laurence', '+49 123 456 789', 'Charlottenstrasse 22', '10117', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a0'), 'Elizabeth', 'Lincoln', 'elizabeth@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', 'elizabeth', '+49 123 456 789', 'Pariser Platz 7', '10117', 'ROLE_CUSTOMER');

--
-- Dumping data for table carts
--

INSERT INTO `kcj`.`carts` (`cart_id`, `customer_id`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b1'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b2'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a2')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b3'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a3')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b4'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a4')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b5'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a5')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b6'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a6')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b7'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a7')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b8'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a8')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b9'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a9')),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49b0'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a0'));

--
-- Dumping data for table employees
--

INSERT INTO `kcj`.`employees` (`employee_id`, `last_name`, `first_name`,  `email`, `nickname`, `password`, `phone_number`, `role`, `restaurant_id`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c1'), 'Davolio', 'Nancy', 'davolio@mail.com', 'nancy', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_USER', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c2'), 'Fuller', 'Ewa', 'fuller@mail.com', 'ewa', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 'ROLE_USER', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c3'), 'Leverling', 'Janet', 'leverling@mail.com', 'janet', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 'ROLE_USER', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c4'), 'Peacock', 'Margaret', 'peacock@mail.com', 'margaret', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 'ROLE_USER', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c5'), 'Buchanan', 'Steven', 'buchanan@mail.com', 'steven', '$2a$10$T4Ti17zkvQMube2OlAcuNepOKNp6QE8vDFk18p51ZwbHNk1jbyOoy', '+49 123 456 789', 'ROLE_USER', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c6'), 'Suyama', 'Michael', 'suyama@mail.com', 'manager', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_MANAGER', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c7'), 'King', 'Robert', 'king@mail.com', 'robert', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_MANAGER', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c8'), 'Callahan', 'Laura', 'callahan@mail.com', 'admin', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_ADMIN', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c9'), 'Dodsworth', 'Anne', 'dodsworth@mail.com', 'anne', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 'ROLE_ADMIN', 1),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c0'), 'West', 'Adam', 'west@mail.com', 'adam', '$2a$10$rmQi5Z5O1HzHsV.6lM8.D..6gAf5vWQPtYwnlNzwWbfq4Ww8ZMDV.', '+49 123 456 789', 'ROLE_ADMIN', 1);

--
-- Dumping data for table products
--

INSERT INTO `kcj`.`products` (`name`, `description`, `price`, `restaurant_id`, `image_url`) VALUES
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
-- Dumping data for table orders
--

INSERT INTO `kcj`.`orders` (`order_id`, `customer_id`, `restaurant_id`, `employee_id`, `delivery_address`, `postal_code`, `total_amount`, `order_status`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49d1'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'), '1', UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c1'), 'Taczaka 2', '61891', '26', 'CREATED'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49d2'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a2'), '1', UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49c2'), 'Limanowskiego 21', '61540', '14', 'CREATED');

--
-- Dumping data for table order_product
--

INSERT INTO `kcj`.`order_products` (`order_product_id`, `order_id`, `product_id`, `quantity`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f41d1'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49d1'), '1', '2'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f41d2'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49d1'), '2', '1'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f41d3'), UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49d2'), '1', '1');

--
-- Dumping data for table `review`
--

INSERT INTO `kcj`.`reviews` (`restaurant_id`, `customer_id`, `rating`, `comment`) VALUES
    ('1', UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'), '5', 'good food'),
    ('1', UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a2'), '4', 'nice'),
    ('1', UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a3'), '2', 'not tasty, I didnt like the food');

COMMIT;