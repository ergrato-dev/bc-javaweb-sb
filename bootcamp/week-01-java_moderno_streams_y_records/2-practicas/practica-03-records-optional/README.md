# Práctica 03 — Records, `var` y Optional

## 🎯 Objetivo
Modelar datos con `record`, usar `var` apropiadamente y eliminar `null` con `Optional`.

## ⏱️ Duración estimada: 60 minutos

---

## Paso 1: Crear un Record básico

```java
// Un record genera automáticamente: constructor, getters, equals, hashCode, toString
public record Point(double x, double y) {}

Point p = new Point(3.0, 4.0);
System.out.println(p.x());    // getter generado
System.out.println(p);        // toString generado: Point[x=3.0, y=4.0]
```

**Abre `starter/RecordsStarter.java`** y descomenta la sección `// PASO 1`.

---

## Paso 2: Compact Constructor para validación

El compact constructor valida invariantes al momento de construcción.

```java
public record Percentage(int value) {
    public Percentage {
        if (value < 0 || value > 100)
            throw new IllegalArgumentException("Must be 0-100");
    }
}
```

**Descomenta la sección `// PASO 2`** y ejecuta. Confirma que lanza excepción con `-1`.

---

## Paso 3: `var` en contextos apropiados

```java
// ✅ var cuando el tipo es obvio
var name   = "Spring";          // String
var count  = 42;                // int
var items  = new ArrayList<String>();  // ArrayList<String>

// ❌ var cuando no es obvio
var result = process();  // ¿qué tipo retorna?
```

**Descomenta la sección `// PASO 3`** y observa que el compilador infiere el tipo correcto.

---

## Paso 4: Optional.ofNullable y map

Transforma valores opcionales encadenando `.map()`.

```java
Optional<String> maybe = Optional.ofNullable(getValue());
String upper = maybe.map(String::toUpperCase)
                    .orElse("DEFAULT");
```

**Descomenta la sección `// PASO 4`**. Verifica que cuando `getValue()` retorna `null`, el resultado es `"DEFAULT"`.

---

## Paso 5: orElseThrow en búsquedas

Simula una búsqueda que puede no encontrar resultado.

**Descomenta la sección `// PASO 5`**. Ajusta el ID buscado entre uno existente y uno inexistente para ver ambos comportamientos.

---

## Paso 6: ifPresentOrElse

```java
optional.ifPresentOrElse(
    value  -> System.out.println("Found: " + value),
    ()     -> System.out.println("Not found")
);
```

**Descomenta la sección `// PASO 6`**.

---

## ✅ Verificación Final

- Todos los pasos compilan y ejecutan sin errores
- Compact constructor lanza excepción con valores inválidos
- Optional no usa `.get()` directamente en ninguna sección

## 📚 Recursos
- [Records — JEP 395](https://openjdk.org/jeps/395)
- [Optional Javadoc](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html)
