------- "TABLE" TABLE -------

INSERT INTO Table (number, capacity)
VALUES
-- Tavoli da 2 persone
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),

-- Tavoli da 4 persone
(11, 4),
(12, 4),
(13, 4),
(14, 4),
(15, 4),
(16, 4),
(17, 4),
(18, 4),
(19, 4),
(20, 4),

-- Tavoli da 6 persone
(21, 6),
(22, 6),
(23, 6),
(24, 6),
(25, 6),

-- Tavoli da 8 persone
(26, 8),
(27, 8),
(28, 8),

-- Tavoli da 12 persone
(29, 12),
(30, 12);


------- "CATEGORY" TABLE -------

INSERT INTO Category (description)
VALUES ('Appetizer'),
       ('First Course'),
       ('Main Course'),
       ('Sides'),
       ('Desserts'),
       ('Beverages');


------- "DISH" TABLE -------
INSERT INTO Dish (name, description, price, category_id, is_available)
VALUES
-- Desserts
('Chocolate Cake', 'Rich chocolate cake with a smooth ganache', 4.50,
 (SELECT id FROM Category WHERE description = 'Desserts'), TRUE),
('Cheesecake', 'Classic cheesecake with a graham cracker crust', 5.00,
 (SELECT id FROM Category WHERE description = 'Desserts'), TRUE),
('Tiramisu', 'Traditional Italian dessert with coffee and mascarpone', 5.50,
 (SELECT id FROM Category WHERE description = 'Desserts'), TRUE),
('Apple Pie', 'Homemade apple pie with a flaky crust', 4.00, (SELECT id FROM Category WHERE description = 'Desserts'),
 TRUE),
('Panna Cotta', 'Creamy panna cotta with a berry compote', 4.75,
 (SELECT id FROM Category WHERE description = 'Desserts'), TRUE),

-- Sides
('French Fries', 'Crispy golden french fries', 2.50, (SELECT id FROM Category WHERE description = 'Sides'), TRUE),
('Caesar Salad', 'Fresh romaine lettuce with Caesar dressing', 3.75,
 (SELECT id FROM Category WHERE description = 'Sides'), TRUE),
('Garlic Bread', 'Toasted bread with garlic butter', 3.00, (SELECT id FROM Category WHERE description = 'Sides'), TRUE),
('Mashed Potatoes', 'Creamy mashed potatoes with gravy', 2.75, (SELECT id FROM Category WHERE description = 'Sides'),
 TRUE),

-- First Course
('Spaghetti Carbonara', 'Classic spaghetti with pancetta, egg, and cheese', 8.50,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),
('Lasagna', 'Layered pasta with meat sauce and cheese', 9.00,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),
('Penne Arrabbiata', 'Penne pasta with a spicy tomato sauce', 7.50,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),
('Fettuccine Alfredo', 'Fettuccine with creamy Alfredo sauce', 8.75,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),
('Ravioli', 'Stuffed pasta with ricotta and spinach', 9.25,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),
('Gnocchi', 'Potato dumplings with a tomato basil sauce', 8.25,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),
('Tagliatelle Bolognese', 'Tagliatelle with a rich meat sauce', 9.50,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),
('Tortellini', 'Cheese-filled tortellini with pesto sauce', 8.00,
 (SELECT id FROM Category WHERE description = 'First Course'), TRUE),

-- Beverages
('Still Water', 'Bottled still water', 1.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Sparkling Water', 'Bottled sparkling water', 1.50, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Coca Cola', 'Classic Coca Cola', 2.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Sprite', 'Lemon-lime soda', 2.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Fanta', 'Orange flavored soda', 2.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Beer', 'Bottled beer', 3.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Red Wine 1', 'House red wine', 4.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Red Wine 2', 'Premium red wine', 5.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Red Wine 3', 'Imported red wine', 6.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('Red Wine 4', 'Exclusive red wine', 7.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('White Wine 1', 'House white wine', 4.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('White Wine 2', 'Premium white wine', 5.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('White Wine 3', 'Imported white wine', 6.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),
('White Wine 4', 'Exclusive white wine', 7.00, (SELECT id FROM Category WHERE description = 'Beverages'), TRUE),

-- Main Course
('Grilled Chicken', 'Chicken breast grilled to perfection', 12.50,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Beef Steak', 'Juicy beef steak with seasoning', 15.00, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Salmon Fillet', 'Grilled salmon fillet with lemon', 14.75,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Vegetarian Pizza', 'Pizza with assorted vegetables', 11.00,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Margherita Pizza', 'Classic margherita pizza', 10.50, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Pepperoni Pizza', 'Pizza topped with pepperoni slices', 11.50,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('BBQ Ribs', 'Slow-cooked ribs with BBQ sauce', 16.00, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Lamb Chops', 'Grilled lamb chops with herbs', 18.00, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Vegetarian Burger', 'Burger with a vegetarian patty', 9.50,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Cheeseburger', 'Classic cheeseburger with fries', 10.00, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Fish and Chips', 'Battered fish with fries', 12.00, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Pork Chops', 'Grilled pork chops with apple sauce', 13.00,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Roast Chicken', 'Roast chicken with vegetables', 14.00, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Turkey Sandwich', 'Turkey sandwich with lettuce and tomato', 8.00,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Shrimp Scampi', 'Shrimp sautéed in garlic butter sauce', 16.50,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Vegan Tacos', 'Tacos with a vegan filling', 10.00, (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Chicken Alfredo', 'Chicken with Alfredo pasta', 13.50, (SELECT id FROM Category WHERE description = 'Main Course'),
 TRUE),
('Stuffed Peppers', 'Bell peppers stuffed with rice and meat', 11.50,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Beef Stroganoff', 'Beef in a creamy mushroom sauce', 14.50,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),
('Eggplant Parmesan', 'Breaded eggplant with marinara sauce', 12.00,
 (SELECT id FROM Category WHERE description = 'Main Course'), TRUE),

-- Appetizers
('Bruschetta', 'Toasted bread with tomatoes and basil', 5.50, (SELECT id FROM Category WHERE description = 'Appetizer'),
 TRUE),
('Stuffed Mushrooms', 'Mushrooms stuffed with cheese', 6.00, (SELECT id FROM Category WHERE description = 'Appetizer'),
 TRUE),
('Mozzarella Sticks', 'Fried mozzarella sticks with marinara', 6.50,
 (SELECT id FROM Category WHERE description = 'Appetizer'), TRUE),
('Calamari', 'Fried calamari with dipping sauce', 7.00, (SELECT id FROM Category WHERE description = 'Appetizer'),
 TRUE),
('Garlic Shrimp', 'Shrimp sautéed with garlic', 8.00, (SELECT id FROM Category WHERE description = 'Appetizer'), TRUE),
('Caprese Salad', 'Tomatoes and mozzarella with basil', 7.50, (SELECT id FROM Category WHERE description = 'Appetizer'),
 TRUE),
('Chicken Wings', 'Spicy chicken wings', 8.50, (SELECT id FROM Category WHERE description = 'Appetizer'), TRUE),
('Nachos', 'Tortilla chips with cheese and salsa', 7.75, (SELECT id FROM Category WHERE description = 'Appetizer'),
 TRUE),
('Spring Rolls', 'Crispy vegetable spring rolls', 6.00, (SELECT id FROM Category WHERE description = 'Appetizer'),
 TRUE),
('Crab Cakes', 'Crab cakes with remoulade sauce', 9.00, (SELECT id FROM Category WHERE description = 'Appetizer'),
 TRUE),
('Onion Rings', 'Crispy fried onion rings', 5.75, (SELECT id FROM Category WHERE description = 'Appetizer'), TRUE);


----- "SLOT" TABLE -----

INSERT INTO slot (description, time)
VALUES ('First Slot', '19:00:00'),
       ('Second Slot', '21:00:00'),
       ('Third Slot', '23:00:00');