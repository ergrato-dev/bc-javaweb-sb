package com.bootcamp.taskapi.domain;

/**
 * Roles disponibles en la aplicación.
 * ADMIN: puede crear/eliminar usuarios y acceder a endpoints de administración.
 * USER: acceso estándar a sus propios recursos.
 */
public enum Role {
    ADMIN,
    USER
}
