CREATE TABLE IF NOT EXISTS product_categories
(
    id   serial PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS products
(
    id           serial PRIMARY KEY,
    category_id  serial,
    name         VARCHAR(100),
    brand        VARCHAR(50),
    manufacturer VARCHAR(100),
    FOREIGN KEY (category_id) references product_categories (id)
);


INSERT INTO product_categories (id, name)
VALUES (1, 'TELEVISION');

INSERT INTO products (category_id, name, brand, manufacturer)
VALUES (1, 'LG Television', 'LG', 'LG');

-- INSERT INTO product_categories (id, name)
-- VALUES (1, 'GAMING');
--
-- INSERT INTO products (category_id, name, brand, manufacturer)
-- VALUES (1, 'PS4', 'Sony', 'Sony');
-- INSERT INTO products (category_id, name, brand, manufacturer)
-- VALUES (1, 'Xbox-360', 'Microsoft', 'Microsoft');

