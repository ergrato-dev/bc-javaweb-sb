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

## 📌 Regla de Oro: Versiones Pinadas (Dependency Pinning)

> **PROHIBIDO usar versiones flotantes o rangos de versión en cualquier `pom.xml` del bootcamp.**

### ❌ Formatos prohibidos en Maven

```xml
<!-- ❌ PROHIBIDO — versiones flotantes o rangos -->
<version>LATEST</version>
<version>RELEASE</version>
<version>[3.0,4.0)</version>   <!-- rango cerrado -->
<version>[3.0,)</version>      <!-- rango abierto hacia arriba -->
<version>(,3.0]</version>      <!-- rango abierto hacia abajo -->
```

> **Nota:** Los operadores `^` y `>=` son notación de npm/package.json — **no son válidos en Maven**.
> En Maven el equivalente peligroso es `LATEST`, `RELEASE` y los rangos `[x,y)`.

### ✅ Formato correcto: versión exacta siempre

```xml
<!-- ✅ CORRECTO — versión exacta fija -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.4.13</version>          <!-- EXACTA: nunca LATEST ni rangos -->
</parent>

<properties>
    <jjwt.version>0.12.7</jjwt.version>         <!-- propiedad con versión exacta -->
    <mapstruct.version>1.6.3</mapstruct.version>
    <lombok.version>1.18.44</lombok.version>
</properties>
```

### 📋 Catálogo de versiones actuales (Abril 2026)

Todas las dependencias del bootcamp han sido auditadas y fijadas a las siguientes versiones:

| Dependencia | Versión pinada | BOM gestionado | Notas |
|---|---|---|---|
| `spring-boot-starter-parent` | `3.4.13` | — | Todos los proyectos semanas 02–16 |
| `org.junit.jupiter:junit-jupiter` | `5.11.4` | No (week-01 standalone) | Semana 01 (Java puro) |
| `org.apache.maven.plugins:maven-surefire-plugin` | `3.5.5` | No (week-01) | Semana 01 (Java puro) |
| `org.springdoc:springdoc-openapi-starter-webmvc-ui` | `2.8.16` | No | Semanas 04, 08, 14, 15, 16 |
| `io.jsonwebtoken:jjwt-*` | `0.12.7` | No | Semanas 11, 16 |
| `org.jacoco:jacoco-maven-plugin` | `0.8.14` | No | Semanas 12, 13, 14, 16 |
| `org.mapstruct:mapstruct` | `1.6.3` | No | Semanas 08, 14, 16 |
| `org.projectlombok:lombok` | `1.18.44` | Parcial (BOM lo define) | Semanas 14, 16 explícito |
| `org.projectlombok:lombok-mapstruct-binding` | `0.2.0` | No | Semanas 14, 16 |
| `org.testcontainers:*` | `1.20.6` | Parcial (BOM lo define) | Semana 16 explícito |

**Dependencias gestionadas exclusivamente por el BOM de Spring Boot** (no requieren `<version>` explícita):
`h2`, `postgresql`, `flyway-core`, `flyway-database-postgresql`, `spring-security-test`,
`spring-boot-testcontainers`, `caffeine`, `jackson-*`, `hibernate-*`, `slf4j-*`, `logback-*`.

### 🔄 Proceso de actualización de versiones

1. **Consultar** el [Spring Boot Release Notes](https://github.com/spring-projects/spring-boot/releases) para versiones del BOM
2. **Verificar CVEs** en [NVD NIST](https://nvd.nist.gov/) y [OSV](https://osv.dev/) antes de actualizar
3. **Actualizar** `pom.xml` con la versión exacta nueva — jamás un rango
4. **Correr** los tests para validar compatibilidad
5. **Documentar** la versión actualizada en esta tabla

---

_Última actualización: Abril 2026_
