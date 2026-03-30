-- V1__create_banking_schema.sql

CREATE TABLE IF NOT EXISTS accounts (
    id         VARCHAR(36)    PRIMARY KEY,
    owner_name VARCHAR(100)   NOT NULL,
    balance    DECIMAL(15,2)  NOT NULL DEFAULT 0.00,
    currency   VARCHAR(3)     NOT NULL,
    status     VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE'
);
