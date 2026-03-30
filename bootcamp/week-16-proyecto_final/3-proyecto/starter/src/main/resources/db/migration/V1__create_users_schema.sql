-- V1: Crear tabla de usuarios
-- Esta tabla almacena los usuarios de la aplicación.
-- La contraseña está siempre hasheada con BCrypt.

CREATE TABLE app_users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    email       VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(20)  NOT NULL DEFAULT 'USER',
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

-- Índices para búsquedas frecuentes
CREATE INDEX idx_app_users_email    ON app_users(email);
CREATE INDEX idx_app_users_username ON app_users(username);
