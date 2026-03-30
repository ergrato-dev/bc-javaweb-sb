# Práctica 01 — Lambdas y Functional Interfaces

## 🎯 Objetivo
Crear y combinar expresiones lambda usando las interfaces funcionales del JDK.

## ⏱️ Duración estimada: 45 minutos

---

## Paso 1: Abrir el archivo starter

Abre `starter/LambdasStarter.java` en tu editor.

El archivo contiene código comentado que deberás descomentar paso a paso.

---

## Paso 2: Predicate básico

Entender un `Predicate<T>`: función que recibe `T` y retorna `boolean`.

```java
// Ejemplo
Predicate<String> isEmail = s -> s.contains("@");
System.out.println(isEmail.test("user@example.com")); // true
System.out.println(isEmail.test("notanemail"));        // false
```

**Abre `starter/LambdasStarter.java`** y descomenta la sección `// PASO 2`.

Ejecuta y verifica que la salida sea:
```
true
false
```

---

## Paso 3: Function y transformaciones

`Function<T, R>` transforma un valor de tipo `T` a tipo `R`.

```java
// Ejemplo — convertir String a Integer
Function<String, Integer> toLength = s -> s.length();

// Equivalente con referencia
Function<String, Integer> toLength = String::length;
```

**Descomenta la sección `// PASO 3`** y verifica la salida:
```
5
7
HELLO
```

---

## Paso 4: Consumer y efectos laterales

`Consumer<T>` recibe un valor y no retorna nada. Útil para imprimir o loggear.

```java
// Ejemplo
Consumer<String> logger = msg -> System.out.println("[LOG] " + msg);
logger.accept("Starting process");
```

**Descomenta la sección `// PASO 4`** y verifica la salida:
```
[LOG] Starting process
[LOG] Item processed
```

---

## Paso 5: Supplier y creación lazy

`Supplier<T>` no recibe parámetros y retorna un valor. Ideal para creación lazy.

```java
// Ejemplo — mensaje de error generado solo si es necesario
Supplier<String> errorMsg = () -> "Error at " + LocalDateTime.now();
```

**Descomenta la sección `// PASO 5`**.

---

## Paso 6: Composición de Predicates

Los predicados se pueden combinar con `.and()`, `.or()`, `.negate()`.

```java
Predicate<String> notEmpty = s -> !s.isEmpty();
Predicate<String> longStr  = s -> s.length() > 5;
Predicate<String> valid    = notEmpty.and(longStr);
```

**Descomenta la sección `// PASO 6`** y verifica que filtra correctamente.

---

## Paso 7: Referencias a métodos

Reemplaza lambdas triviales con referencias `::`.

**Descomenta la sección `// PASO 7`** y reemplaza cada lambda con su referencia equivalente.

---

## ✅ Verificación Final

Al ejecutar `LambdasStarter.java` debes ver en consola todas las salidas de los 6 pasos sin errores de compilación.

## 📚 Recursos
- [Java Functional Interfaces — Oracle Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/package-summary.html)
