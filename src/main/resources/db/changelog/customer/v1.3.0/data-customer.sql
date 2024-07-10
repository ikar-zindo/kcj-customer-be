-- liquibase formatted sql
-- changeset customer:v1.3.0-data-customer
-- comment add data

--
-- Dumping data for tables
--

--
-- Dumping data for table customers
--

INSERT INTO `customers` (`customer_id`, `first_name`, `last_name`, `email`, `password`, `phone_number`, `address`, `postal_code`, `role`) VALUES
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a1'), 'Maria', 'Skłodowska-Curie',	'maria@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', '+49 123 456 789', 'Obere Str. 57', '12209', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a2'), 'Super', 'Mario',	'super@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', '+49 123 456 789', 'Minerstrasse 33', '10115', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a3'), 'Ultron', '', 'ultron@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', '+49 123 456 789', 'Alexanderplatz 3', '10178', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a4'), 'Robin', 'Hood',	'robin@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', '+49 123 456 789', 'Friedrichstrasse 123', '10117', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a5'), 'Yeshua', 'HaNotzri', 'yeshua@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', '+49 123 456 789', 'Karl-Liebknecht-Strasse 29', '10178', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a6'), 'Bugs', 'Bunny', 'bugs@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', '+49 123 456 789', 'Potsdamer Platz 1', '10785', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a7'), 'Santa', 'Klaus', 'santa@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', '+49 123 456 789', 'Stralauer Strasse 34', '10243', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a8'), 'Luke', 'Skywalker', 'luke@mail.com', '$2a$10$xJCiY3.jw5qjxdggiFGtrOQyyX0P62KAO/uqtbYwiEWBZ1iFTr1mm', '+49 123 456 789', 'Lichtenberger Strasse 11', '10179', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a9'), 'Peter', 'Parker', 'peter@mail.com', '$2a$10$lKwjA8yqzzackBa2SjUkJOocYJqNy6SP/ntOuh4wnOuxmFcVvQC62', '+49 123 456 789', 'Charlottenstrasse 22', '10117', 'ROLE_CUSTOMER'),
    (UUID_TO_BIN('d234d99d-170e-42f7-b6ae-435ee56f49a0'), 'Frodo', 'Baggins', 'frodo@mail.com', '$2a$10$fBZ/JeO6EOWLfx4CaPDqQe92VWrYJTPl0D0znpMBg1R2hMwp3C.m.', '+49 123 456 789', 'Pariser Platz 7', '10117', 'ROLE_CUSTOMER');

--
-- Dumping data for table carts
--

INSERT INTO `carts` (`cart_id`, `customer_id`) VALUES
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

COMMIT;