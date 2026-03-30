-- V1: Create Inventory schema
CREATE TABLE products (
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(255)   NOT NULL,
    sku        VARCHAR(100)   NOT NULL UNIQUE,
    price      DECIMAL(10, 2) NOT NULL,
    stock      INT            NOT NULL DEFAULT 0,
    category   VARCHAR(100)   NOT NULL,
    created_at TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);

CREATE INDEX idx_products_sku ON products (sku);
CREATE INDEX idx_products_category ON products (category);
