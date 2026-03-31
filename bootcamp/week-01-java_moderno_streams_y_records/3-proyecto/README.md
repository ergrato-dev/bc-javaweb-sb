# Proyecto Semana 01 — Procesador de Datos con Java Moderno

## 🎯 Descripción

Construye una utillería de procesamiento de datos que demuestre el uso idiomático de Java 21: Streams API, Records, Optional y switch expressions. No hay Spring esta semana — solo Java puro.

## 📋 Escenario

Una empresa de logística necesita procesar listas de envíos y generar reportes. Tú construirás el motor de procesamiento usando Java moderno.

## 🏗️ Modelo de Datos

```java
public record Shipment(
    String trackingId,
    String origin,
    String destination,
    double weightKg,
    ShipmentStatus status,
    LocalDate estimatedDelivery
) {}

public enum ShipmentStatus { PENDING, IN_TRANSIT, DELIVERED, CANCELLED }
```

## 📌 Requerimientos

### Funcionales
- [ ] **R1:** Filtrar envíos por estado (`filterByStatus(List<Shipment>, ShipmentStatus)`)
- [ ] **R2:** Calcular valor declarado total de todos los envíos (`totalDeclaredValue(List<Shipment>)`)
- [ ] **R3:** Buscar envío por ID (retornar `Optional<Shipment>`)
- [ ] **R4:** Agrupar envíos por destino (`Map<String, List<Shipment>>`)
- [ ] **R5:** Generar resumen estadístico por estado (conteo de cada estado)
- [ ] **R6:** Obtener top 3 envíos con mayor peso
- [ ] **R7:** Verificar si todos los envíos tienen estado final (DELIVERED o CANCELLED)

### Técnicos
- [ ] Cada requerimiento implementado como método con firma definida
- [ ] Usar Streams API para todas las operaciones de colección (sin bucles `for`)
- [ ] Usar `Optional` en R3 — no retornar `null`
- [ ] Usar Records para `Shipment` y para el resultado del resumen R5
- [ ] Demostrar uso de `var` en al menos 3 variables locales
- [ ] Usar switch expression (no switch statement) en al menos un lugar

> 💡 **R4** usa `groupByStatus` (Map por estado). **R6** usa `top3MostValuable` (declaredValue desc). **R7** usa `allDelivered` (allMatch).

## 🧪 Tests Mínimos

Crea una clase `ShipmentProcessorTest` con al menos 5 métodos `@Test` que verifiquen los requerimientos. Usa `mvn test` para correrlos.

## 📂 Estructura Sugerida

```
src/
├── main/java/com/bootcamp/
│   ├── model/
│   │   ├── Shipment.java          (record)
│   │   ├── ShipmentStatus.java    (enum)
│   │   └── ShipmentSummary.java   (record para R5)
│   └── service/
│       └── ShipmentProcessor.java (métodos R1-R7)
└── test/java/com/bootcamp/
    └── service/
        └── ShipmentProcessorTest.java
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| R1-R7 implementados y correctos | 50 |
| Uso de Streams (sin bucles `for`) | 20 |
| Optional, Records, var, switch expression | 20 |
| Tests compilando y pasando (`mvn test`) | 10 |
| **Total** | **100** |
