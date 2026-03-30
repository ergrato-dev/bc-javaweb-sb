# 🔒 Política de Seguridad

## Versiones Soportadas

Este es un repositorio **educativo**. El código de los ejercicios y proyectos está diseñado
para el aprendizaje, no para uso en producción directamente.

| Versión / Rama | Soportada |
|----------------|-----------|
| `main`         | ✅        |
| Ramas antiguas | ❌        |

---

## Reportar una Vulnerabilidad

Si encuentras una vulnerabilidad de seguridad en este repositorio, por favor **NO** abras
un Issue público. Sigue estos pasos:

### 1. Vulnerabilidades en el código de ejemplo

Si el código de algún ejercicio o proyecto contiene un patrón inseguro que podría enseñar
malas prácticas a los estudiantes (ej. SQL injection, credenciales hardcodeadas, CORS abierto):

1. Abre un [Issue privado / Security Advisory](https://github.com/ergrato-dev/bc-javaweb-sb/security/advisories/new) en GitHub
2. Describe el archivo afectado y la naturaleza del problema
3. Si es posible, sugiere la corrección

Responderemos en un plazo máximo de **7 días hábiles**.

### 2. Credenciales o datos sensibles expuestos accidentalmente

Si detectas credenciales reales, tokens o datos personales expuestos en el repositorio,
repórtalo de inmediato vía [Security Advisory](https://github.com/ergrato-dev/bc-javaweb-sb/security/advisories/new).

---

## Prácticas de Seguridad del Bootcamp

Este bootcamp enseña explícitamente las siguientes prácticas de seguridad:

- ✅ Validación de inputs con Jakarta Bean Validation (`@Valid`)
- ✅ Hashing de contraseñas con BCrypt (Spring Security)
- ✅ Autenticación JWT y OAuth2
- ✅ Autorización con roles (`@PreAuthorize`)
- ✅ Nunca exponer entidades JPA directamente — usar DTOs
- ✅ Variables de entorno para credenciales (`.env`, nunca hardcodeado)
- ✅ HTTPS en producción
- ✅ Configuración CORS restrictiva
- ✅ Migraciones de BD con Flyway (no `ddl-auto: create`)

---

## Aviso sobre Código de Ejemplo

El código en este repositorio es **ilustrativo y educativo**. Antes de usar cualquier
fragmento en un entorno de producción:

1. Realiza una revisión de seguridad completa
2. Consulta el [OWASP Top 10](https://owasp.org/www-project-top-ten/)
3. Actualiza las dependencias a sus versiones más recientes
4. Adapta la configuración a tu contexto específico

---

_Última actualización: Marzo 2026_
