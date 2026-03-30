# Práctica 02 — Adaptadores en Spring Boot

## 🎯 Objetivo

Conectar el dominio puro (Práctica 01) a Spring Boot mediante adaptadores: un adaptador de entrada (Controller) y un adaptador de salida (JPA Repository).

## 🔧 Pasos

Abre `starter/AdaptersApp.java` y descomenta por bloques.

### Paso 1: Puerto de entrada (Use Case Interface)
La interfaz en `application/port/in/` — el controller la usa.

### Paso 2: Adaptador de salida JPA
Implementa el puerto de salida con Spring Data JPA.

### Paso 3: Servicio de aplicación
Implementa el Use Case con Spring `@Service`.

### Paso 4: Adaptador de entrada (Controller)
Expone el Use Case via REST.

### Paso 5: Mapper dominio ↔ JPA
Convierte entre Account (dominio) y AccountJpaEntity (infraestructura).

## ✅ Verificación

Arrancar la aplicación y probar:
```bash
# Crear cuenta
curl -X POST /api/accounts -d '{"currency":"USD","initialBalance":1000}'

# Depositar
curl -X POST /api/accounts/{id}/deposits -d '{"amount":500,"currency":"USD"}'

# Ver balance
curl /api/accounts/{id}
```
