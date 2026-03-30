# Práctica 2: Mockito Avanzado

## 🎯 Objetivo

Aplicar `ArgumentCaptor`, `spy`, `verify()` e `inOrder()` para testear interacciones entre objetos.

---

## Estructura del ejercicio

```
practica-02-mockito-avanzado/
└── starter/
    └── MockitoAdvancedApp.java   ← archivo único con todo el código
```

---

## Paso 1: ArgumentCaptor — Capturar argumentos

Cuando necesitas verificar **qué datos exactos** se pasaron a un método del mock:

```java
@Captor
private ArgumentCaptor<Loan> loanCaptor;

service.createLoan("alice", bookId);

verify(loanRepository).save(loanCaptor.capture());
var saved = loanCaptor.getValue();

assertThat(saved.getUsername()).isEqualTo("alice");
assertThat(saved.getStatus()).isEqualTo(LoanStatus.ACTIVE);
```

**Abre `starter/MockitoAdvancedApp.java`** y descomenta `PASO 1`.

---

## Paso 2: Spy — Mock parcial de objeto real

Un spy ejecuta el código real pero permite stubbear métodos específicos:

```java
var serviceSpy = spy(new LoanService(repo));

// Solo stubbear canLoan() — el resto ejecuta código real
doReturn(true).when(serviceSpy).canLoan(anyString());

serviceSpy.createLoan("alice", 1L); // ejecuta código real de createLoan
verify(serviceSpy).canLoan("alice"); // verificamos que se llamó
```

**Descomenta `PASO 2`**.

---

## Paso 3: verify() con tiempos y orden

```java
// Verificar número de invocaciones
verify(repo).save(any());                   // exactamente 1 vez
verify(emailService, never()).send(any());  // nunca llamado

// Verificar orden de invocaciones
var order = inOrder(bookRepo, loanRepo);
order.verify(bookRepo).findById(anyLong()); // primero
order.verify(loanRepo).save(any());         // después
```

**Descomenta `PASO 3`** y observa qué pasa si cambias el orden.

---

## Paso 4: doAnswer — Simular efectos secundarios

```java
// Para métodos void que deben causar efectos secundarios
doAnswer(invocation -> {
    Loan loan = invocation.getArgument(0);
    loan.setId(99L); // simular que la DB asignó un ID
    return null;
}).when(loanRepository).save(any());
```

**Descomenta `PASO 4`** y ejecuta los tests con `mvn test`.

