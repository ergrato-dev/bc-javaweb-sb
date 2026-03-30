-- V2__seed_data.sql

INSERT INTO customers (name, email, phone) VALUES
    ('Alice Johnson', 'alice@example.com', '+1-555-0001'),
    ('Bob Smith',     'bob@example.com',   '+1-555-0002'),
    ('Carol White',   'carol@example.com', '+1-555-0003');

INSERT INTO products (name, description, price, stock, category) VALUES
    ('Laptop Pro 15',    'High performance laptop',  1299.99, 50,  'Electronics'),
    ('Wireless Mouse',   'Ergonomic wireless mouse',    29.99, 200, 'Electronics'),
    ('USB-C Hub',        '7-port USB-C hub',            49.99, 150, 'Electronics'),
    ('Desk Lamp',        'LED adjustable desk lamp',    34.99, 100, 'Office'),
    ('Coffee Mug',       'Insulated travel mug',        19.99, 300, 'Kitchen');
