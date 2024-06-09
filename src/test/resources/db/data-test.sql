--
-- Dumping data for tables
--

--
-- Dumping data for table restaurants
--

INSERT INTO restaurants (name, address, phone_number, opening_hours, cuisine_type, description, social_media_links) VALUES
    ('K-Curry Jib', 'Alexanderplaz 1, 10178', '+49 123 456 789', '12:00 - 22:00', 'Asian food', '', 'social-media-link.com');

--
-- Dumping data for table customers
--

INSERT INTO customers (customer_id, first_name, last_name, email, password, phone_number, address, postal_code, role) VALUES
    (CAST('d234d99d-170e-42f7-b6ae-435ee56f49a1' AS BINARY(16)), 'Maria', 'Anders',	'maria@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', '+49 123 456 789', 'Obere Str. 57', '12209', 'ROLE_CUSTOMER'),
    (CAST('d234d98d-170e-42f7-b6ae-435ee56f49a2' AS BINARY(16)), 'Ana', 'Trujillo',	'ana@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', '+49 123 456 789', 'Minerstrasse 33', '10115', 'ROLE_CUSTOMER');

--
-- Dumping data for table carts
--

INSERT INTO carts (cart_id, customer_id) VALUES
    (CAST('d234d99d-170e-42f7-b6ae-435ee56f49b1' AS BINARY(16)), CAST('d234d99d-170e-42f7-b6ae-435ee56f49a1' AS BINARY(16)));

--
-- Dumping data for table employees
--

INSERT INTO employees (employee_id, last_name, first_name,  email, nickname, password, phone_number, role, restaurant_id) VALUES
    (CAST('d234d99d-170e-42f7-b6ae-435ee56f49c1' AS BINARY(16)), 'Davolio', 'Nancy', 'davolio@mail.com', 'nancy', '$2a$10$OebBU653Mokfh/uRVu9CCexVAN3LBrkHpAtHZP6iMdZj8JmldwNqW', '+49 123 456 789', 'ROLE_USER', 1);

--
-- Dumping data for table products
--

INSERT INTO products (name, description, price, restaurant_id, image_url) VALUES
    ('Chicken Curry', 'Fried chicken with vegetables and rice in medium spicy curry sauce', 14, 1, '1.jpg');

--
-- Dumping data for table orders
--

INSERT INTO orders (order_id, customer_id, restaurant_id, employee_id, delivery_address, postal_code, total_amount, order_status) VALUES
    (CAST('d234d99d-170e-42f7-b6ae-435ee56f49d1' AS BINARY(16)), CAST('d234d99d-170e-42f7-b6ae-435ee56f49a1' AS BINARY(16)), '1', CAST('d234d99d-170e-42f7-b6ae-435ee56f49c1' AS BINARY(16)), 'Taczaka 2', '61891', '26', 'CREATED');

--
-- Dumping data for table order_product
--

INSERT INTO order_products (order_product_id, order_id, product_id, quantity) VALUES
    (CAST('d234d99d-170e-42f7-b6ae-435ee56f41d1' AS BINARY(16)), CAST('d234d99d-170e-42f7-b6ae-435ee56f49d1' AS BINARY(16)), '1', '2');

--
-- Dumping data for table `review`
--

INSERT INTO reviews (restaurant_id, customer_id, rating, comment) VALUES
    ('1', CAST('d234d99d-170e-42f7-b6ae-435ee56f49a1' AS BINARY(16)), '5', 'good food'),
    ('1', CAST('d234d99d-170e-42f7-b6ae-435ee56f49a1' AS BINARY(16)), '5', 'good food');