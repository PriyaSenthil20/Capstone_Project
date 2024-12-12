BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, customers, orders, order_status, products, product_type, size, order_products, orders_selections, product_options, options_type, payment, transfer_type, drivers CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,

	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE customers (
	customer_id int NOT NULL,
	customer_first_name varchar(30) NOT NULL,
	customer_last_name varchar(30) NOT NULL,
	customer_address varchar(30) NOT NULL,
	customer_phone_number varchar(20) NOT NULL,
	customer_city varchar(30) NOT NULL,
	customer_state varchar(2) NOT NULL,
	customer_zip_code int NOT NULL,
	customer_email_address varchar(40) NOT NULL UNIQUE,

	CONSTRAINT PK_customers PRIMARY KEY (customer_id), 
	CONSTRAINT FK_customers_user FOREIGN KEY (customer_id) REFERENCES users(user_id)	
);

CREATE TABLE order_status (
	status_id serial NOT NULL,
	status_type varchar(20) NOT NULL,
	
	CONSTRAINT PK_order_status PRIMARY KEY (status_id)
);

CREATE TABLE transfer_type (
	transfer_id serial NOT NULL,
	transfer_name varchar(10) NOT NULL,

	CONSTRAINT PK_transfer_type PRIMARY KEY (transfer_id)
);

CREATE TABLE drivers (
	driver_id int NOT NULL,
	driver_name varchar(40),

	CONSTRAINT PK_driver_id PRIMARY KEY (driver_id),
	CONSTRAINT FK_driver_id_user_id FOREIGN KEY (driver_id) REFERENCES users (user_id)
);
	
CREATE TABLE orders (
	order_id serial NOT NULL,
	customer_id int NOT NULL,
	transfer_id int NOT NULL,
	driver_id int,
	notes varchar (200),
	total_sale float NOT NULL,
	pickup_date varchar (10) NOT NULL,
	pickup_time varchar (10) NOT NULL,
	status_id int NOT NULL,

	CONSTRAINT PK_orders PRIMARY KEY (order_id), 
	CONSTRAINT FK_orders_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id), 
	CONSTRAINT FK_orders_drivers FOREIGN KEY (driver_id) REFERENCES drivers(driver_id),
	CONSTRAINT FK_order_status FOREIGN KEY (status_id) REFERENCES order_status(status_id),
	CONSTRAINT FK_transfer_type FOREIGN KEY (transfer_id) REFERENCES transfer_type(transfer_id)
);

CREATE TABLE product_type (
	product_type_id serial NOT NULL,
	product_name varchar(30) NOT NULL,

	CONSTRAINT PK_product_type PRIMARY KEY (product_type_id)
);

CREATE TABLE size (
	size_id serial NOT NULL,
	size_style varchar(20) NOT NULL,
	price_multiplier float NOT NULL,

	CONSTRAINT PK_size PRIMARY KEY (size_id)
);

CREATE TABLE options_type (
	options_type_id serial,
	description varchar (20),

	CONSTRAINT PK_options_Type PRIMARY KEY (options_type_id)
);

CREATE TABLE product_options (
	option_id serial NOT NULL,
	option_name varchar(30) NOT NULL,
	option_desc varchar(200) NOT NULL,
	option_type_id int NOT NULL,
	option_price float NOT NULL,
	option_available boolean NOT NULL,
	
	CONSTRAINT PK_product_options PRIMARY KEY (option_id), 
	CONSTRAINT FK_product_options_type FOREIGN KEY (option_type_id) REFERENCES options_type(options_type_id)
);

CREATE TABLE products (
	product_id serial NOT NULL,
	product_name varchar(30) NOT NULL,
	product_desc varchar(200) NOT NULL,
	product_type_id int NOT NULL,
	product_price float NOT NULL,
	product_available boolean NOT NULL,
	size_id int NOT NULL,

	CONSTRAINT PK_products PRIMARY KEY (product_id), 
	CONSTRAINT FK_product_type FOREIGN KEY (product_type_id) REFERENCES product_type(product_type_id), 
	CONSTRAINT FK_size FOREIGN KEY (size_id) REFERENCES size(size_id)
);

CREATE TABLE order_products (
	order_product_id serial NOT NULL,
	order_id int NOT NULL,
	product_id int NOT NULL,
	product_sale_price float NOT NULL,

	CONSTRAINT PK_order_products PRIMARY KEY (order_product_id),
	CONSTRAINT FK_order_products_order FOREIGN KEY (order_id) REFERENCES orders(order_id),
	CONSTRAINT FK_order_products_product FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE orders_selections (
	order_selection_id serial NOT NULL,
	order_product_id serial NOT NULL,
	order_id int NOT NULL,
	product_id int NOT NULL,
	option_id int NOT NULL,
	option_sale_price int NOT NULL,

	CONSTRAINT PK_order_selections PRIMARY KEY (order_selection_id),
	CONSTRAINT FK_order_selections_order_products FOREIGN KEY (order_product_id) REFERENCES order_products(order_product_id),
	CONSTRAINT FK_order_selections_order FOREIGN KEY (order_id) REFERENCES orders(order_id),
	CONSTRAINT FK_order_selections_product FOREIGN KEY (product_id) REFERENCES products(product_id),
	CONSTRAINT FK_order_selections_option FOREIGN KEY (option_id) REFERENCES product_options(option_id)
	
);
	
CREATE TABLE payment (
	payment_id serial NOT NULL,
	order_id int NOT NULL,
	payment_type varchar (20) NOT NULL,
	card_number varchar (16) NOT NULL,
	card_expiration varchar (10) NOT NULL,
	card_cvv int NOT NULL,
	card_zipcode int NOT NULL,
	
	CONSTRAINT PK_payment PRIMARY KEY (payment_id), 
	CONSTRAINT FK_payment_order FOREIGN KEY (order_id) REFERENCES orders(order_id)
);


INSERT INTO order_status (status_type)
VALUES ('Pending'),
('Canceled'),
('Ready'),
('Complete');

INSERT INTO size (size_style, price_multiplier)
VALUES ('Small', 1.3),
('Medium', 1.5),
('Large', 1.7),
('Anthony', 1.8);

INSERT INTO transfer_type (transfer_name)
VALUES ('Delivery'),
('Pickup');

INSERT INTO product_type (product_name)
VALUES ('Special Pizza'),
('Custom Pizza'),
('Drink');

INSERT INTO options_type(description)
VALUES ('Topping'),
('Sauce'),
('Crust'),
('Premium');

INSERT INTO product_options(option_name, option_desc, option_type_id, option_price, option_available)
VALUES ('None', 'None', 1, 0.00, true),
('Pepperoni', 'Pepperoni', 1, 2.00, true),
('Bacon', 'Bacon', 1, 2.00, true),
('Onions', 'Onions', 1, 2.00, true),
('Green Pepper', 'Green Pepper', 1, 2.00, true),
('Mushrooms', 'Mushrooms', 1, 2.00, true),
('Onions', 'Onions', 1, 2.00, true),
('Sausage', 'Sausage', 1, 2.00, true),
('Marinara', 'Marinara', 2, 0.00, true),
('Alfredo', 'Alfredo', 2, 0.00, true),
('Pesto', 'Pesto', 2, 0.00, true),
('Traditional','Traditional', 3, 0.00, true),
('Deep Dish','Deep Dish', 3, 2.00, true),
('Steak','Steak', 4, 3.50, true),
('Chicken','Chicken', 4, 3.50, true);


INSERT INTO products(product_name, product_desc, product_type_id, product_price, product_available, size_id)
VALUES ('Custom Pizza', 'Small Custom Pizza', 2, 10, true, 1),
('Custom Pizza', 'Medium Custom Pizza', 2, 13, true, 2),
('Custom Pizza', 'Large Custom Pizza', 2, 15, true, 3),
('Coca Cola', 'Regular Coca Cola', 3, 3, true, 2),
('Coca Cola', 'Large Coca Cola', 3, 5, true, 3),
('Rocco Special', 'Medium Special Pizza', 1, 15, true, 2),
('Rocco Special', 'Large Special Pizza', 1, 22, true, 3);
('Apollo Special', 'A supreme blend of toppings for a truly satisfying bite.', 1, 15.99, true, 2);
('Lei'Ed', 'A masterful selection for a pizza king.', 1, 15.99, true, 2);
('The Master', 'Can you handle the Master?', 1, 15.99, true, 2);
	
COMMIT TRANSACTION;
