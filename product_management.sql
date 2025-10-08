CREATE DATABASE product_management;

USE product_management;

CREATE TABLE product(
id int AUTO_INCREMENT PRIMARY key,
name VARCHAR(120) not null,
price DOUBLE not null,
quantity int not null,
color VARCHAR(100) NOT null,
category VARCHAR(120) NOT null
);