# Práctica 02 — Streams API

## 🎯 Objetivo
Construir pipelines de procesamiento de datos con operaciones intermedias y terminales.

## ⏱️ Duración estimada: 60 minutos

---

## Contexto

Trabajarás con una lista de productos de una tienda. En cada paso añadirás operaciones al pipeline para obtener resultados diferentes.

---

## Paso 1: filter y map básicos

```java
// Ejemplo — filtrar productos caros y obtener sus nombres
List<String> expensiveNames = products.stream()
        .filter(p -> p.price() > 100)
        .map(Product::name)
        .toList();
```

**Abre `starter/StreamsStarter.java`** y descomenta la sección `// PASO 1`.

Salida esperada:
```
[Laptop, Monitor, Keyboard]
```

---

## Paso 2: sorted y distinct

Ordena productos por precio de mayor a menor, sin duplicados de categoría.

```java
// Ejemplo — ordenar strings por longitud
list.stream()
    .sorted(Comparator.comparingInt(String::length).reversed())
    .toList();
```

**Descomenta la sección `// PASO 2`**.

---

## Paso 3: reduce y collect

Calcula el precio total de los productos filtrados.

```java
// Ejemplo — suma con reduce
double total = products.stream()
        .mapToDouble(Product::price)
        .sum();
```

**Descomenta la sección `// PASO 3`** y verifica el total impreso.

---

## Paso 4: groupingBy

Agrupa los productos por categoría usando `Collectors.groupingBy`.

```java
// Ejemplo
Map<String, List<Product>> byCategory =
    products.stream()
            .collect(Collectors.groupingBy(Product::category));
```

**Descomenta la sección `// PASO 4`** y verifica que imprime una entrada por categoría.

---

## Paso 5: statistics con summarizingDouble

Obtén el precio mínimo, máximo y promedio de todos los productos.

**Descomenta la sección `// PASO 5`**.

Salida esperada:
```
Min: 9.99 | Max: 999.0 | Avg: 249.99
```
*(valores aproximados según los datos del starter)*

---

## Paso 6: flatMap

Trabaja con una lista de listas (órdenes de compra con múltiples ítems).

```java
// Ejemplo — aplanar listas anidadas
List<String> allItems = orders.stream()
        .flatMap(order -> order.items().stream())
        .toList();
```

**Descomenta la sección `// PASO 6`**.

---

## ✅ Verificación Final

Ejecuta `StreamsStarter.java` y verifica que cada sección produce la salida correcta según los comentarios.

## 📚 Recursos
- [Stream Javadoc — Java 21](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/Stream.html)
- [Collectors Javadoc](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/Collectors.html)
