# Semana 10 — Spring Security: Task Management API

## 🎯 Objetivo del Proyecto

Implementar seguridad completa en una API de gestión de tareas con **autenticación HTTP Basic**,
**control de acceso por roles** (`ROLE_USER` / `ROLE_ADMIN`) y **UserDetailsService** que carga
usuarios desde PostgreSQL.

## 📋 Descripción

La API permite a usuarios gestionar sus propias tareas y a administradores supervisar todo el sistema.

### Roles y Permisos

| Endpoint | ROLE_USER | ROLE_ADMIN |
|----------|-----------|------------|
| `POST /api/auth/register` | ✅ público | ✅ público |
| `GET /api/tasks` | ✅ solo las suyas | ✅ todas |
| `POST /api/tasks` | ✅ | ✅ |
| `PUT /api/tasks/{id}` | ✅ solo las suyas | ✅ cualquiera |
| `DELETE /api/tasks/{id}` | ❌ | ✅ |
| `GET /api/admin/users` | ❌ | ✅ |
| `DELETE /api/admin/users/{id}` | ❌ | ✅ |

## 🏗️ Estructura del Proyecto

```
src/main/java/com/bootcamp/
├── domain/
│   ├── AppUser.java          # Entidad usuario con roles
│   ├── Role.java             # Enum: ROLE_USER, ROLE_ADMIN
│   ├── Task.java             # Entidad tarea
│   └── TaskStatus.java       # Enum: PENDING, IN_PROGRESS, DONE, CANCELLED
├── repository/
│   ├── UserRepository.java
│   └── TaskRepository.java
├── dto/
│   └── Dtos.java             # RegisterRequest, TaskCreateRequest, TaskResponse...
├── security/
│   ├── SecurityConfig.java   # FilterChain, BCrypt, CORS
│   └── CustomUserDetailsService.java  # Carga usuarios desde DB
├── service/
│   ├── UserService.java      # Registro, listado, desactivación
│   └── TaskService.java      # CRUD de tareas con control de acceso
├── controller/
│   ├── UserController.java   # /api/auth/register, /api/admin/users
│   └── TaskController.java   # /api/tasks
└── exception/
    ├── UserNotFoundException.java
    ├── TaskNotFoundException.java
    └── GlobalExceptionHandler.java
```

## 📝 TODOs a Implementar

### UserService.java
1. `register()` — validar username único, hashear password con BCrypt, asignar ROLE_USER por defecto
2. `findAll()` — listar usuarios paginados
3. `findByUsername()` — buscar usuario por username
4. `deactivate()` — soft delete (setActive(false))

### TaskService.java
1. `findMyTasks()` — lista tareas del usuario autenticado
2. `findAll()` — lista todas las tareas (admin)
3. `findById()` — obtiene tarea verificando ownership
4. `create()` — crea tarea asignando ownerUsername
5. `update()` — actualiza campos no nulos verificando ownership
6. `delete()` — elimina tarea (admin solo)

### UserController.java
1. `register()` — retornar 201 Created
2. `findAll()` — anotar con @PreAuthorize ADMIN, retornar 200
3. `findByUsername()` — anotar con @PreAuthorize ADMIN, retornar 200
4. `deactivate()` — anotar con @PreAuthorize ADMIN, retornar 204

### TaskController.java
1. `findTasks()` — diferenciar entre admin (todo) y usuario (solo suyas)
2. `findById()` — pasar username y isAdmin al servicio
3. `create()` — pasar ownerUsername del usuario autenticado
4. `update()` — pasar username y isAdmin
5. `delete()` — anotar con @PreAuthorize ADMIN, retornar 204

## 🧪 Verificación

Los tests en `UserControllerTest.java` están completos. Ejecuta:

```bash
./mvnw test
```

Todos los tests deben pasar después de implementar los TODOs.

## 🔒 Probar con curl

```bash
# Registrar usuario
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "alice", "password": "alice123", "role": "ROLE_USER"}'

# Registrar admin
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123", "role": "ROLE_ADMIN"}'

# Crear tarea (como alice)
curl -u alice:alice123 -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Mi primera tarea", "description": "Descripción de la tarea"}'

# Alice ve sus tareas
curl -u alice:alice123 http://localhost:8080/api/tasks

# Admin ve todas las tareas
curl -u admin:admin123 http://localhost:8080/api/tasks

# Alice intenta endpoint admin → 403
curl -u alice:alice123 http://localhost:8080/api/admin/users

# Admin gestiona usuarios
curl -u admin:admin123 http://localhost:8080/api/admin/users
```

## 📊 Rúbrica de Evaluación

| Criterio | Puntos |
|----------|--------|
| Registro funciona y hashea password con BCrypt | 20 |
| Autenticación HTTP Basic funciona | 15 |
| ROLE_USER solo ve sus propias tareas | 20 |
| ROLE_ADMIN puede ver todo y eliminar | 20 |
| 401 sin credenciales, 403 sin permisos | 15 |
| Tests pasan (`./mvnw test`) | 10 |
| **Total** | **100** |
