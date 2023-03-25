CREATE DATABASE product;
CREATE TABLE product_categories
(
    id   serial PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE products
(
    id           serial PRIMARY KEY,
    category_id  serial,
    name         VARCHAR(100),
    brand        VARCHAR(50),
    manufacturer VARCHAR(100),
    FOREIGN KEY (category_id) references product_categories (id)
);