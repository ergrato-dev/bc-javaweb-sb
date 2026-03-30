# Glosario — Semana 01: Java Moderno

Términos clave ordenados alfabéticamente.

---

## C

**Collector**
Estrategia de reducción en Streams. Acumula los elementos de un Stream en una estructura de datos. Ejemplos: `Collectors.toList()`, `Collectors.groupingBy()`, `Collectors.joining()`.

**Compact Constructor**
Forma compacta de constructor en un `record` usada para validar parámetros antes de que se asignen. No redeclara los parámetros — solo ejecuta lógica de validación o normalización.

**Consumer\<T\>**
Interfaz funcional que recibe un argumento de tipo `T` y no retorna resultado. Representa una operación con efecto lateral, como imprimir o loggear.

---

## F

**flatMap**
Operación intermedia de Stream que aplana un `Stream<Stream<T>>` en `Stream<T>`. Útil para trabajar con listas anidadas o cuando el mapper retorna un Optional.

**Functional Interface**
Interfaz con exactamente un método abstracto (`@FunctionalInterface`). Base del sistema de lambdas en Java. Ejemplos: `Runnable`, `Callable`, `Predicate`, `Function`.

**Function\<T, R\>**
Interfaz funcional que recibe un argumento de tipo `T` y retorna un resultado de tipo `R`. Se compone con `.andThen()` y `.compose()`.

---

## G

**groupingBy**
Collector que agrupa elementos de un Stream en un `Map<K, List<T>>` usando una función clasificadora. Equivale al `GROUP BY` de SQL.

---

## I

**Immutable**
Objeto cuyo estado no puede cambiar después de ser creado. Los `record` en Java son inmutables por diseño — todos sus campos son `final`.

**Intermediate Operation**
Operación de Stream que retorna otro Stream (lazy). No se ejecuta hasta que hay una operación terminal. Ejemplos: `filter`, `map`, `sorted`, `distinct`.

---

## L

**Lambda**
Función anónima que puede pasarse como argumento o asignarse a una variable de tipo interfaz funcional. Sintaxis: `(params) -> body`.

**Lazy Evaluation**
Las operaciones intermedias de un Stream no se ejecutan hasta que se invoca una operación terminal. Permite optimizaciones como short-circuit.

---

## M

**map**
Operación intermedia que transforma cada elemento del Stream aplicando una `Function<T, R>`. Similar a `forEach` pero retorna un nuevo Stream transformado.

**Method Reference (`::`)**
Forma compacta de lambda que referencia un método existente. Tipos: estático (`Clase::metodo`), por instancia de objeto (`obj::metodo`), por tipo (`Tipo::metodo`), constructor (`Clase::new`).

---

## O

**Optional\<T\>**
Contenedor que puede o no contener un valor no-nulo. Evita `NullPointerException` y hace explícita la posibilidad de ausencia. Retornado típicamente por métodos de búsqueda.

**orElseGet**
Método de `Optional` que retorna el valor si está presente, o evalúa un `Supplier<T>` para obtener el valor por defecto. Preferible a `orElse` cuando el valor por defecto es costoso de crear.

**orElseThrow**
Método de `Optional` que retorna el valor si está presente, o lanza la excepción provista por el `Supplier`. Patrón estándar en servicios Spring para lanzar excepciones de dominio.

---

## P

**Pattern Matching (instanceof)**
Feature de Java 16+ que combina la comprobación de tipo y el cast en una sola expresión: `if (obj instanceof String s)`.

**Predicate\<T\>**
Interfaz funcional que recibe `T` y retorna `boolean`. Se compone con `.and()`, `.or()`, `.negate()`.

---

## R

**Record**
Clase de datos inmutable en Java 14+. El compilador genera automáticamente constructor, getters (por nombre), `equals()`, `hashCode()` y `toString()`. Ideal para DTOs y value objects.

**reduce**
Operación terminal que combina los elementos del Stream usando un `BinaryOperator`. Ejemplo: sumar todos los valores. Variante con identidad retorna `T`, sin identidad retorna `Optional<T>`.

---

## S

**Stream\<T\>**
Secuencia de elementos que soporta operaciones de procesamiento declarativo. No almacena datos — describe transformaciones sobre una fuente. Se consume una sola vez.

**Supplier\<T\>**
Interfaz funcional sin parámetros que retorna `T`. Útil para creación lazy, generación de valores por defecto y factories.

**switch expression**
Forma moderna del switch (Java 14+) que retorna un valor directamente. Usa `->` en lugar de `:`, elimina el `break` y el compilador verifica exhaustividad.

---

## T

**Terminal Operation**
Operación de Stream que produce un resultado (eager) y consume el Stream. Ejemplos: `collect`, `count`, `reduce`, `forEach`, `findFirst`, `anyMatch`.

---

## V

**var**
Palabra clave para inferencia de tipos local (Java 11+). El compilador deduce el tipo estático en el punto de declaración. Solo válido para variables locales, no para campos ni parámetros.

**Value Object**
Patrón de diseño donde dos objetos son iguales si tienen los mismos valores, no la misma identidad. Los `record` en Java implementan este patrón automáticamente.
