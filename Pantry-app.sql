DROP SCHEMA IF EXISTS pantry_app_db;
CREATE SCHEMA pantry_app_db;
USE pantry_app_db;

CREATE TABLE pantry (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  creation_date DATE,
  user_id BIGINT,
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  category VARCHAR(255),
  barcode BIGINT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE product_in_pantry (
    pantry_id BIGINT,
    product_id BIGINT,
    quantity INT,
    FOREIGN KEY (pantry_id) REFERENCES pantry (id),
    FOREIGN KEY (product_id) REFERENCES product (id),
	PRIMARY KEY(product_id, pantry_id)
);
  
INSERT INTO pantry (id, name, creation_date) VALUES
(1, 'Apartment Own', '2021-01-05'),
(2, 'Apartment Shared', '2021-01-06');

INSERT INTO product (id, name, category, barcode) VALUES
(1, 'Soy Milk', 'MILK', 123456),
(2, 'Chicken Wings', 'MEAT', 333222),
(3, 'Coriander', 'VEGETABLES',  111222),
(4, 'Avocado', 'VEGETABLES', 999888),
(5, 'Soap', 'CLEAN_PRODUCTS', 000007),
(6, 'Lentils', 'VEGETABLES', 127456),
(7, 'Beans', 'CANNED_PRODUCTS', 334222),
(8, 'Potatoes', 'VEGETABLES',  111522),
(9, 'Carrots', 'VEGETABLES', 999288),
(10, 'Water', 'BEVERAGE', 000337),
(11, 'Cheese', 'FRESH_PRODUCTS', 120056),
(12, 'Cucumber', 'VEGETABLES', 333212),
(13, 'Nuts', 'NUTS',  114122),
(14, 'Beer', 'ALCOHOLIC_DRINKS', 993888),
(15, 'Eggs', 'FRESH_PRODUCTS', 000001),
(16, 'Fish', 'FRESH_PRODUCTS', 124406),
(17, 'Ribs', 'MEAT', 331232),
(18, 'Tomato Sauce', 'CANNED_PRODUCTS',  119002),
(19, 'Tomatoes', 'VEGETABLES', 951488),
(20, 'Chocolate', 'SWEETS', 967288),
(21, 'Rice', 'GRAIN', 001987);

INSERT INTO product_in_pantry (pantry_id, product_id, quantity) VALUES
(1, 1, 5),
(2, 1, 3),
(2, 2, 1),
(1, 3, 2),
(2, 3, 3);

DROP SCHEMA IF EXISTS pantry_app_user_db;
CREATE SCHEMA pantry_app_user_db;
USE pantry_app_user_db;

CREATE TABLE `user` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  `password` VARCHAR(255),
  email varchar(255) UNIQUE,
  username VARCHAR(36) UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE `role` (
  user_id BIGINT NOT NULL,
  status VARCHAR(255),
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES `user` (id)
);

INSERT INTO user (id, name, password, email, username) VALUES
(1, 'my user', '123', 'random@email.com', 'myUser'),
(2, 'my user', '123', 'random2@email.com', 'myUser2');