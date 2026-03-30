# Práctica 01 — Primer Value Object y Dominio Puro

## 🎯 Objetivo

Crear un modelo de dominio puro (sin Spring, sin JPA) con Value Objects y entidades con comportamiento.

## 🔧 Pasos

Abre `starter/HexagonalApp.java` y descomenta por bloques.

### Paso 1: Value Object `Money`
Record inmutable con validación y operaciones aritméticas.

### Paso 2: Value Object `AccountId`
Strongly-typed ID para evitar confundir IDs entre entidades.

### Paso 3: Entidad de Dominio `Account`
Cuenta bancaria con comportamiento: deposit/withdraw con reglas de negocio.

### Paso 4: Puerto de salida `AccountRepository`
Interfaz — contrato que el adaptador de infraestructura implementará.

### Paso 5: Use Case `DepositMoneyUseCase`
Servicio de aplicación que usa el puerto de salida.

## ✅ Verificación

Los tests de dominio deben pasar sin ninguna dependencia de Spring.
Ejecutar: `javac HexagonalApp.java && java HexagonalApp`
