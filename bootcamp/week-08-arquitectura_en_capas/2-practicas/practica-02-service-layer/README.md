# Práctica 02 — Service Layer: Lógica de Negocio Compleja

## 🎯 Objetivo

Implementar un Service Layer con **lógica de negocio compleja**: cálculos, reglas de estado, orquestación de múltiples repositories.

## 📋 Escenario

Una librería permite prestar libros a miembros. Reglas de negocio:

- Un miembro puede tener máximo **3 préstamos activos**
- Un libro solo puede prestarse si está **disponible** (no prestado)
- El préstamo tiene fecha de devolución **14 días** después del préstamo
- Si se devuelve tarde, el sistema registra la devolución como **overdue**

## 🔧 Pasos

Abre `starter/ServiceLayerApp.java` y completa descomentando por secciones.

### Paso 1: Dominio (Book, Member, Loan)

Descomenta el bloque `PASO 1` — entidades JPA.

### Paso 2: Repositories con queries útiles

Descomenta el bloque `PASO 2` — `findByMemberAndStatus`, `existsByBookAndStatus`.

### Paso 3: LoanService con reglas de negocio

Descomenta el bloque `PASO 3` — validación de límite 3 préstamos, disponibilidad del libro, cálculo de fecha de devolución.

### Paso 4: Controller

Descomenta el bloque `PASO 4` — endpoints REST: POST /loans (prestar), PATCH /loans/{id}/return (devolver).

## ✅ Verificación

Al intentar un 4to préstamo activo → 422 Unprocessable Entity  
Al intentar prestar un libro no disponible → 409 Conflict  
Al devolver un préstamo fuera de fecha → status = OVERDUE
