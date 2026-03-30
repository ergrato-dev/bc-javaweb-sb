# Proyecto — E-Library API: Suite de Tests Completa

## 📋 Descripción

Recibes la **E-Library API** completamente implementada (BookService, LoanService, controladores). Tu misión es escribir una suite de tests exhaustiva que cubra la lógica de negocio.

## 🎯 Objetivo

Escribir tests para un sistema existente — la habilidad más común en el trabajo real.

---

## 📂 Estructura

```
starter/
├── pom.xml
└── src/
    ├── main/java/com/bootcamp/elibrary/
    │   ├── ELibraryApiApplication.java
    │   ├── controller/
    │   │   ├── BookController.java      ← NO modificar
    │   │   └── LoanController.java      ← NO modificar
    │   ├── domain/
    │   │   ├── Book.java                ← NO modificar
    │   │   ├── BookStatus.java
    │   │   ├── Loan.java                ← NO modificar
    │   │   └── LoanStatus.java
    │   ├── dto/Dtos.java
    │   ├── exception/
    │   │   ├── BookNotFoundException.java
    │   │   ├── LoanNotFoundException.java
    │   │   └── GlobalExceptionHandler.java
    │   ├── repository/
    │   │   ├── BookRepository.java
    │   │   └── LoanRepository.java
    │   └── service/
    │       ├── BookService.java         ← NO modificar
    │       └── LoanService.java        ← NO modificar
    └── test/java/com/bootcamp/elibrary/
        ├── controller/
        │   └── BookControllerTest.java  ← ✏️ IMPLEMENTAR AQUÍ
        └── service/
            ├── BookServiceTest.java     ← ✏️ IMPLEMENTAR AQUÍ
            └── LoanServiceTest.java    ← ✏️ IMPLEMENTAR AQUÍ
```

---

## 🏗️ Reglas de Negocio de LoanService

Entiéndelas bien antes de escribir los tests:

| Regla | Descripción |
|---|---|
| Max 3 préstamos | Un usuario no puede tener más de 3 préstamos ACTIVE simultáneamente |
| Disponibilidad | Un libro debe tener `availableCopies > 0` para ser prestado |
| Solo el dueño | Solo el usuario que sacó el libro puede devolverlo |
| Estado ACTIVE | Solo se puede devolver un préstamo con estado ACTIVE |

---

## 📋 Tareas

### 1. `BookServiceTest.java` — Tests unitarios del servicio

Implementa todos los métodos marcados con `// TODO`:

- [ ] `findAll` — devuelve página con libros / devuelve vacío
- [ ] `findById` — devuelve libro / lanza BookNotFoundException
- [ ] `create` — guarda libro / lanza en duplicado / captura campos (ArgumentCaptor)
- [ ] `update` — actualiza campos / lanza si no existe
- [ ] `delete` — llama deleteById / lanza si no existe

### 2. `LoanServiceTest.java` — Tests unitarios con reglas de negocio

- [ ] `createLoan` — crea préstamo / verifica orden (inOrder) / captura campos (ArgumentCaptor)
- [ ] `createLoan` — lanza si 3 préstamos activos / lanza si libro no existe / lanza si sin copias
- [ ] `returnBook` — cambia estado / lanza si loan no pertenece al usuario / lanza si ya devuelto
- [ ] `findByUsername` — devuelve lista / devuelve vacío

### 3. `BookControllerTest.java` — Tests de capa web

- [ ] `GET /api/books` → 200 con lista
- [ ] `GET /api/books/{id}` → 200 o 404
- [ ] `POST /api/books` → 201 + Location header / 400 validación / 400 ISBN duplicado
- [ ] `PUT /api/books/{id}` → 200 o 404
- [ ] `DELETE /api/books/{id}` → 204 o 404

---

## ▶️ Comandos

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests de una clase específica
mvn test -Dtest=BookServiceTest

# Ver reporte de cobertura (se genera en target/site/jacoco/index.html)
mvn test jacoco:report

# Abrir reporte en navegador
xdg-open target/site/jacoco/index.html  # Linux
open target/site/jacoco/index.html      # Mac
```

---

## 🏆 Criterios de Evaluación

| Criterio | Descripción | Puntos |
|---|---|---|
| BookServiceTest completo | Todos los métodos implementados | 30% |
| LoanServiceTest — reglas | Tests de las 4 reglas de negocio | 35% |
| BookControllerTest | Todos los códigos HTTP correctos | 25% |
| ArgumentCaptor / verify | Uso de al menos 2 captores | 10% |

### Aprobación

- ✅ Todos los tests deben pasar (`BUILD SUCCESS`)
- ✅ Mínimo 80% de cobertura en `BookService` y `LoanService` (JaCoCo)
- ✅ Al menos un `@ParameterizedTest` o `@Nested` por archivo
