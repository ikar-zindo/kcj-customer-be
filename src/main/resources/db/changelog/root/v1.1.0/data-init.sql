-- liquibase formatted sql
-- changeset root:v1.1.0-data-init
-- comment adding initial data

--
-- Dumping data for tables
--

--
-- Dumping data for table restaurants
--

INSERT INTO `restaurants` (`name`, `address`, `phone_number`, `opening_hours`, `cuisine_type`, `description`, `social_media_links`) VALUES
    ('K-Curry Jib', 'Alexanderplaz 1, 10178', '+49 123 456 789', '12:00 - 22:00', 'Asian food', '', 'social-media-link.com'),
    ('Zindo', 'Alexanderplaz 1, 10178', '+49 123 456 789', '12:00 - 22:00', 'Asian food', '', 'social-media-link.com');

--
-- Dumping data for table products
--

INSERT INTO `products` (`name`, `description`, `price`, `restaurant_id`, `image_url`) VALUES
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

COMMIT;