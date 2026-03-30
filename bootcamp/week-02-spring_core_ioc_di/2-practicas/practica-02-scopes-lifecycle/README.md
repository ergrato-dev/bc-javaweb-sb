# Práctica 02 — Bean Scopes y Ciclo de Vida

## 🎯 Objetivo
Observar los escopos `singleton` y `prototype` y los hooks `@PostConstruct`/`@PreDestroy`.

## ⏱️ Duración estimada: 45 minutos

---

## Paso 1: Singleton — instancia compartida

Crea dos referencias al mismo `@Service` y verifica que comparten la misma instancia.

```java
// Un singleton retorna siempre la misma instancia
UserService svc1 = context.getBean(UserService.class);
UserService svc2 = context.getBean(UserService.class);
System.out.println(svc1 == svc2); // true
```

**Descomenta la sección `// STEP 1`** del starter.

---

## Paso 2: @PostConstruct — inicialización

```java
@PostConstruct
void init() {
    System.out.println("Bean initialized — loading data");
}
```

**Descomenta la sección `// STEP 2`** y observa el orden en la consola:
1. Constructor
2. @PostConstruct
3. Bean ready

---

## Paso 3: @PreDestroy — limpieza

```java
@PreDestroy
void destroy() {
    System.out.println("Bean destroyed — releasing resources");
}
```

**Descomenta la sección `// STEP 3`** y observa que se ejecuta al cerrar la app.

---

## Paso 4: Prototype — nueva instancia cada vez

```java
@Scope("prototype")
@Component
public class ReportBuilder { /* ... */ }
```

**Descomenta la sección `// STEP 4`**. Verifica que cada `getBean(ReportBuilder.class)` retorna **objetos diferentes**.

---

## ✅ Verificación Final

En la consola deberías ver:
```
Singleton: same instance = true
[INIT] UserService initialized
[DESTROY] UserService shutting down
Prototype: same instance = false
```
