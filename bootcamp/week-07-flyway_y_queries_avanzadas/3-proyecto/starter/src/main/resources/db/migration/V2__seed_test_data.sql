-- V2__seed_test_data.sql
-- Insert initial test data for development

INSERT INTO doctors (name, specialty, email, license_no, phone) VALUES
    ('Dr. Ana García',    'Cardiology',   'ana.garcia@hospital.com',    'LIC-001', '+1-555-0101'),
    ('Dr. Carlos López',  'Neurology',    'carlos.lopez@hospital.com',  'LIC-002', '+1-555-0102'),
    ('Dr. María Torres',  'Pediatrics',   'maria.torres@hospital.com',  'LIC-003', '+1-555-0103'),
    ('Dr. Pedro Sánchez', 'Orthopedics',  'pedro.sanchez@hospital.com', 'LIC-004', '+1-555-0104');

INSERT INTO patients (first_name, last_name, email, birth_date, blood_type) VALUES
    ('Alice',   'Johnson', 'alice.j@email.com',   '1990-03-15', 'A+'),
    ('Bob',     'Smith',   'bob.s@email.com',     '1985-07-22', 'O-'),
    ('Carol',   'White',   'carol.w@email.com',   '1995-11-08', 'B+'),
    ('David',   'Brown',   'david.b@email.com',   '1978-01-30', 'AB+');
