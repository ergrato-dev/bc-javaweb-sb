# Mockito Avanzado: Mocks, Spies y Captores

## 🎯 Objetivos

- Capturar argumentos con `ArgumentCaptor`
- Entender la diferencia entre `mock` y `spy`
- Verificar interacciones con `verify()`
- Controlar comportamiento con `doAnswer` y `doThrow`

---

## 1. ArgumentCaptor — Capturar lo que se pasó al mock

```java
@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<Loan> loanCaptor;

    @InjectMocks
    private LoanService loanService;

    @Test
    void createLoan_setsFieldsCorrectly() {
        var book = new Book(1L, "Clean Code", BookStatus.AVAILABLE);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        loanService.createLoan("john", 1L, 14); // 14 días de préstamo

        // Capturamos el objeto Loan que se pasó a save()
        verify(loanRepository).save(loanCaptor.capture());
        Loan saved = loanCaptor.getValue();

        assertThat(saved.getUsername()).isEqualTo("john");
        assertThat(saved.getBook()).isEqualTo(book);
        assertThat(saved.getDueDays()).isEqualTo(14);
        assertThat(saved.getReturnedAt()).isNull(); // aún no devuelto
    }
}
```

---

## 2. Spy — Mock parcial de un objeto real

```java
// mock() — objeto completamente falso, todos los métodos devuelven null/0/false/vacío
var mockList = mock(ArrayList.class);
mockList.add("hello");        // no hace nada real
mockList.size();              // devuelve 0 (el mock no tiene estado)

// spy() — objeto real con capacidad de stubbear métodos específicos
var spyList = spy(new ArrayList<>());
spyList.add("hello");         // SÍ agrega al ArrayList real
spyList.size();               // devuelve 1 (el spy SÍ tiene estado)
doReturn(99).when(spyList).size(); // stubbear solo size()

// En tests de servicio — spy para un servicio que llama a sus propios métodos
@Test
void createLoan_callsAvailabilityCheck() {
    var serviceSpy = spy(loanService);
    doReturn(true).when(serviceSpy).isAvailable(any());

    // el spy ejecuta createLoan real pero intercepta isAvailable()
    serviceSpy.createLoan("john", 1L, 14);

    verify(serviceSpy).isAvailable(any());
}
```

---

## 3. verify() — Verificar interacciones

```java
// Verificar número exacto de invocaciones
verify(loanRepository).save(any());                  // exactly 1 time (default)
verify(loanRepository, times(2)).save(any());        // exactly 2 times
verify(loanRepository, never()).delete(any());       // never called
verify(loanRepository, atLeast(1)).findById(any());  // at least once
verify(loanRepository, atMost(3)).findById(any());   // at most 3 times

// Verificar que no hubo más interacciones
verifyNoMoreInteractions(loanRepository);

// Verificar orden de invocaciones
var order = inOrder(bookRepository, loanRepository);
order.verify(bookRepository).findById(1L);       // primero esto...
order.verify(loanRepository).save(any());        // ...luego esto
```

---

## 4. doAnswer / doThrow

```java
// doThrow — lanzar excepción en métodos void
doThrow(new DataIntegrityViolationException("duplicate"))
    .when(loanRepository).save(any());

// doAnswer — lógica custom cuando el mock es llamado
doAnswer(invocation -> {
    Loan loan = invocation.getArgument(0);
    loan.setId(42L); // simular que DB asignó un ID
    return loan;
}).when(loanRepository).save(any(Loan.class));

// Útil para métodos void:
doAnswer(invocation -> {
    System.out.println("Email enviado a: " + invocation.getArgument(0));
    return null;
}).when(emailService).sendNotification(anyString());
```

---

## 5. @InjectMocks y configuración de mocks

```java
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    // Mockito crea instancia real de BookService
    // e inyecta los @Mock automáticamente (por constructor o campo)
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Test
    void findById_throwsWhenNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.findById(99L))
            .isInstanceOf(BookNotFoundException.class)
            .hasMessage("Book not found: 99");
    }

    @Test
    void findAll_returnsEmpty_whenNoBooks() {
        when(bookRepository.findAll(any(Pageable.class)))
            .thenReturn(Page.empty());

        var result = bookService.findAll(Pageable.unpaged());

        assertThat(result).isEmpty();
        verify(bookRepository).findAll(any(Pageable.class));
        verifyNoMoreInteractions(bookRepository, authorRepository);
    }
}
```

---

## ✅ Checklist

- [ ] `@Captor` + `ArgumentCaptor` para verificar objetos guardados
- [ ] `verify()` con `times()`, `never()`, `inOrder()` para interacciones
- [ ] `spy()` solo cuando necesitas comportamiento real + stubbing parcial
- [ ] `doAnswer()` para simular efectos secundarios en métodos void
- [ ] `verifyNoMoreInteractions()` para detectar llamadas inesperadas
