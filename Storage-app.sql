DROP SCHEMA IF EXISTS storage_app;
CREATE SCHEMA storage_app;
USE storage_app;

CREATE TABLE pantry (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  creation_date DATE,
  owner BIGINT,
  PRIMARY KEY (id)
  );
  
CREATE TABLE product (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  category VARCHAR(255),
  quantity INT,
  pantry_id BIGINT,
  barcode BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (pantry_id) REFERENCES pantry (id)
);
  
CREATE TABLE `user` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  `password` VARCHAR(255),
  email varchar(255) UNIQUE,
  username VARCHAR(36) UNIQUE,
  PRIMARY KEY (id)
);

  CREATE TABLE shared_pantry (
  user_id BIGINT,
  pantry_id BIGINT,
  FOREIGN KEY (pantry_id) REFERENCES pantry (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  PRIMARY KEY (user_id, pantry_id)
  );

CREATE TABLE `role` (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255),
  PRIMARY KEY (user_id),
  FOREIGN KEY (user_id) REFERENCES `user` (id)
);

INSERT INTO pantry (id, name) VALUES
(1, 'Apartment Own'),
(2, 'Apartment Shared');

INSERT INTO product (name, category, quantity, pantry_id, barcode) VALUES
('Soy Milk', 'MILK', 1, 1, 123456),
('Chicken Wings', 'MEAT', 0, 1, 333222),
('Coriander', 'VEGETABLES', 1, 1, 111222),
('Avocado', 'VEGETABLES', 3, 1, 999888),
('Soap', 'CLEAN', 1, 2, 000007);

INSERT INTO user (id, name, password, email, username) VALUES
(1, 'my user', '123', 'random@email.com', 'myUser'),
(2, 'my user', '123', 'random2@email.com', 'myUser2');

INSERT INTO shared_pantry (user_id, pantry_id) VALUES
(1, 1),
(1, 2),
(2, 2)

