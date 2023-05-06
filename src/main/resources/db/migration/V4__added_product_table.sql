CREATE TABLE product(
    id BIGSERIAL PRIMARY KEY,
    product_type varchar(64),
    name varchar(64),
    approved BOOLEAN
)