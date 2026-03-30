# 🏦 Proyecto Semana 09 — Banking API Hexagonal

## 🎯 Objetivo

Construir una API bancaria aplicando **arquitectura hexagonal (Ports & Adapters)**: dominio puro sin frameworks, puertos como interfaces, y adaptadores de infraestructura.

## 🚀 Ejecutar

```bash
./mvnw spring-boot:run
```

H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:bankingdb`)

## 📁 Estructura

```
domain/              ← Entidades + Value Objects (sin Spring)
application/
  port/in/           ← Interfaces de casos de uso
  port/out/          ← Interfaces de repositorio
  usecase/           ← Implementaciones de Use Cases (@Service)
infrastructure/
  adapter/in/web/    ← Controller REST (adaptador primario)
  adapter/out/       ← Adaptador de persistencia JPA
  exception/         ← GlobalExceptionHandler
```

## 📝 Tareas

### 1. `Account.deposit()` y `Account.withdraw()`
Implementa los TODOs en `domain/Account.java`:
- `deposit`: verificar ACTIVE + añadir saldo
- `withdraw`: verificar ACTIVE + verificar saldo suficiente + restar

### 2. `AccountService` — 4 use cases
Implementa los TODOs en `application/usecase/AccountService.java`:
- `createAccount`: crear Account domain + save via port
- `transfer`: cargar ambas cuentas + withdraw/deposit + save
- `getAccount`: cargar + mapear a AccountView
- `listAccounts`: findAll paginado + mapear

### 3. `AccountController` — 4 endpoints
Implementa los TODOs en `infrastructure/adapter/in/web/AccountController.java`

## ✅ Criterios de evaluación

| Criterio | Pts |
|---|---|
| Dominio puro: `Account.deposit/withdraw` sin Spring imports | 20 |
| `AccountService`: los 4 use cases implementados | 35 |
| `AccountController`: 4 endpoints funcionando | 30 |
| Tests de dominio pasan: `AccountTest` (6 tests sin Spring) | 15 |

**Total: 100 puntos**
