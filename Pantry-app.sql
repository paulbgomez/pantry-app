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
(5, 'Soap', 'CLEAN', 000007);

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