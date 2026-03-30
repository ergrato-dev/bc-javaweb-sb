# Práctica 01 — IoC y Constructor Injection

## 🎯 Objetivo
Crear beans con stereotypes y conectarlos mediante constructor injection.

## ⏱️ Duración estimada: 45 minutos

---

## Contexto

Construirás un mini-sistema de biblioteca: `BookRepository` → `BookService` → `LibraryApplication`. Cada clase es un bean de Spring; verás cómo el container los conecta.

---

## Paso 1: Declarar el Repository

```java
// BookRepository usa @Repository para que Spring gestione las excepciones de BD
@Repository
public class BookRepository {
    public List<Book> findAll() { /* ... */ }
}
```

**Abre `starter/LibraryApp.java`** y descomenta la sección `// STEP 1`.

---

## Paso 2: Declarar el Service con constructor injection

```java
@Service
public class BookService {
    private final BookRepository repository; // final — inmutable

    // Constructor injection — Spring detecta el único constructor
    public BookService(BookRepository repository) {
        this.repository = repository;
    }
}
```

**Descomenta la sección `// STEP 2`** en el starter.

---

## Paso 3: Configuración con `@Configuration` y `@Bean`

Declara un bean de tercero (en este caso un `Formatter` que simula una librería externa).

```java
@Configuration
public class AppConfig {
    @Bean
    public Formatter bookFormatter() {
        return new CsvFormatter(); // tercero — no podemos agregar @Component
    }
}
```

**Descomenta la sección `// STEP 3`**.

---

## Paso 4: Inyectar múltiples dependencias

El `BookService` ahora necesita también el `Formatter`.

**Descomenta la sección `// STEP 4`** y observa que basta con agregar el parámetro al constructor.

---

## ✅ Verificación Final

Al ejecutar la app, deberías ver en la consola:
```
BookRepository created
BookService created with BookRepository and CsvFormatter
Formatting book: Effective Java (CSV)
```

## 📚 Recursos
- [Spring IoC Container Docs](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans)
