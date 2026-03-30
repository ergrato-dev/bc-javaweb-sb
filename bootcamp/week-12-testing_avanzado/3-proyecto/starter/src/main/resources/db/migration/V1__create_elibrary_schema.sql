-- V1: Create E-Library schema
CREATE TABLE books (
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title            VARCHAR(255)   NOT NULL,
    isbn             VARCHAR(13)    NOT NULL UNIQUE,
    author           VARCHAR(255)   NOT NULL,
    price            DECIMAL(10, 2) NOT NULL,
    status           VARCHAR(20)    NOT NULL DEFAULT 'AVAILABLE',
    total_copies     INT            NOT NULL DEFAULT 1,
    available_copies INT            NOT NULL DEFAULT 1
);

CREATE TABLE loans (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username     VARCHAR(100) NOT NULL,
    book_id      BIGINT       NOT NULL REFERENCES books (id),
    status       VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE',
    loan_date    DATE         NOT NULL,
    due_date     DATE         NOT NULL,
    returned_at  DATE
);

CREATE INDEX idx_loans_username ON loans (username);
CREATE INDEX idx_loans_status ON loans (status);
