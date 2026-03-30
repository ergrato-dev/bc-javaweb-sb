package com.bootcamp.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * PRÁCTICA 2: Mockito Avanzado
 *
 * Instrucciones:
 * 1. Lee el README.md para entender cada paso
 * 2. Descomenta cada sección en orden
 * 3. Ejecuta los tests con: mvn test
 */
@ExtendWith(MockitoExtension.class)
public class MockitoAdvancedApp {

    // ============================================
    // CLASES DE SOPORTE PARA LOS EJERCICIOS
    // ============================================

    enum LoanStatus { ACTIVE, RETURNED, OVERDUE }

    static class Book {
        Long id;
        String title;
        boolean available;
        public Book(Long id, String title) { this.id = id; this.title = title; this.available = true; }
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }
    }

    static class Loan {
        Long id;
        String username;
        Book book;
        LoanStatus status;
        LocalDate loanDate;
        LocalDate dueDate;

        public Loan() {}
        public Loan(String username, Book book, int days) {
            this.username = username;
            this.book = book;
            this.status = LoanStatus.ACTIVE;
            this.loanDate = LocalDate.now();
            this.dueDate = LocalDate.now().plusDays(days);
        }
        // getters/setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getUsername() { return username; }
        public LoanStatus getStatus() { return status; }
        public LocalDate getDueDate() { return dueDate; }
    }

    interface BookRepository {
        Optional<Book> findById(Long id);
        Book save(Book book);
    }

    interface LoanRepository {
        Loan save(Loan loan);
        List<Loan> findByUsername(String username);
        List<Loan> findByStatus(LoanStatus status);
    }

    interface NotificationService {
        void sendLoanConfirmation(String email, String bookTitle);
    }

    static class LoanService {
        private final BookRepository bookRepository;
        private final LoanRepository loanRepository;
        private final NotificationService notificationService;

        public LoanService(BookRepository bookRepo, LoanRepository loanRepo,
                          NotificationService notifService) {
            this.bookRepository = bookRepo;
            this.loanRepository = loanRepo;
            this.notificationService = notifService;
        }

        public Loan createLoan(String username, Long bookId, int days) {
            var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));
            if (!book.isAvailable()) throw new IllegalStateException("Book not available");
            if (days < 1 || days > 30) throw new IllegalArgumentException("Days must be between 1 and 30");

            book.setAvailable(false);
            bookRepository.save(book);

            var loan = new Loan(username, book, days);
            return loanRepository.save(loan);
        }

        public boolean canLoan(String username) {
            // Business rule: max 3 active loans per user
            return loanRepository.findByUsername(username).stream()
                .filter(l -> l.getStatus() == LoanStatus.ACTIVE)
                .count() < 3;
        }
    }

    // ============================================
    // MOCKS — disponibles para todos los pasos
    // ============================================
    @Mock
    BookRepository bookRepository;

    @Mock
    LoanRepository loanRepository;

    @Mock
    NotificationService notificationService;

    @Captor
    ArgumentCaptor<Loan> loanCaptor;

    @Captor
    ArgumentCaptor<Book> bookCaptor;

    // ============================================
    // PASO 1: ArgumentCaptor
    // ============================================
    // ArgumentCaptor captura el argumento exacto que se pasó al mock,
    // permitiendo hacer assertions detalladas sobre ese objeto.
    // Descomenta las siguientes líneas:

    // @Nested
    // @DisplayName("PASO 1: ArgumentCaptor")
    // class Step1 {
    //
    //     @Test
    //     @DisplayName("captures loan fields when creating")
    //     void capturesLoanOnCreate() {
    //         var book = new Book(1L, "Clean Code");
    //         when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    //         // Cuando se llame save(Loan), retornar el mismo loan
    //         when(loanRepository.save(any(Loan.class)))
    //             .thenAnswer(inv -> inv.getArgument(0));
    //
    //         var service = new LoanService(bookRepository, loanRepository, notificationService);
    //         service.createLoan("alice", 1L, 14);
    //
    //         // Capturamos el Loan que se pasó a loanRepository.save()
    //         verify(loanRepository).save(loanCaptor.capture());
    //         var saved = loanCaptor.getValue();
    //
    //         assertThat(saved.getUsername()).isEqualTo("alice");
    //         assertThat(saved.getStatus()).isEqualTo(LoanStatus.ACTIVE);
    //         assertThat(saved.getDueDate()).isEqualTo(LocalDate.now().plusDays(14));
    //         assertThat(saved.getBook().title).isEqualTo("Clean Code");
    //     }
    //
    //     @Test
    //     @DisplayName("captures book update (available=false) when creating loan")
    //     void capturesBookUnavailableOnCreate() {
    //         var book = new Book(1L, "Refactoring");
    //         when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    //         when(loanRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
    //
    //         new LoanService(bookRepository, loanRepository, notificationService)
    //             .createLoan("bob", 1L, 7);
    //
    //         verify(bookRepository).save(bookCaptor.capture());
    //         assertThat(bookCaptor.getValue().isAvailable()).isFalse();
    //     }
    // }

    // ============================================
    // PASO 2: Spy — Comportamiento parcial real
    // ============================================
    // Un spy ejecuta el código real excepto donde stubbeamos explícitamente.
    // Descomenta:

    // @Nested
    // @DisplayName("PASO 2: Spy")
    // class Step2 {
    //
    //     @Test
    //     @DisplayName("spy calls real canLoan() but we can verify it")
    //     void spyVerifiesCanLoan() {
    //         // El spy SÍ ejecuta el código real de canLoan()
    //         when(loanRepository.findByUsername("alice")).thenReturn(List.of());
    //
    //         var realService = new LoanService(bookRepository, loanRepository, notificationService);
    //         var serviceSpy = spy(realService);
    //
    //         // canLoan() real: alice tiene 0 préstamos → puede pedir (< 3)
    //         assertThat(serviceSpy.canLoan("alice")).isTrue();
    //         verify(serviceSpy).canLoan("alice");
    //     }
    //
    //     @Test
    //     @DisplayName("spy can stub specific methods")
    //     void spyCanStubMethods() {
    //         var book = new Book(1L, "Clean Code");
    //         when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    //         when(loanRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
    //
    //         var realService = new LoanService(bookRepository, loanRepository, notificationService);
    //         var serviceSpy = spy(realService);
    //
    //         // Solo stubbeamos canLoan() — el resto ejecuta código real
    //         doReturn(true).when(serviceSpy).canLoan(anyString());
    //
    //         serviceSpy.createLoan("alice", 1L, 14);
    //
    //         // El spy ejecutó el código real de createLoan(), que llama al repo
    //         verify(loanRepository).save(any());
    //     }
    // }

    // ============================================
    // PASO 3: verify() — Interacciones y orden
    // ============================================
    // Descomenta:

    // @Nested
    // @DisplayName("PASO 3: verify() — interaction verification")
    // class Step3 {
    //
    //     @Test
    //     @DisplayName("verify exact number of calls")
    //     void verifyExactCalls() {
    //         var book = new Book(1L, "Clean Code");
    //         when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    //         when(loanRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
    //
    //         var service = new LoanService(bookRepository, loanRepository, notificationService);
    //         service.createLoan("alice", 1L, 14);
    //
    //         verify(bookRepository, times(1)).findById(1L);  // exactamente 1 vez
    //         verify(bookRepository, times(1)).save(any());   // actualizar disponibilidad
    //         verify(loanRepository, times(1)).save(any());   // guardar préstamo
    //         verify(notificationService, never()).sendLoanConfirmation(any(), any()); // nunca
    //     }
    //
    //     @Test
    //     @DisplayName("verify order of operations")
    //     void verifyOrder() {
    //         var book = new Book(1L, "Clean Code");
    //         when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    //         when(loanRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
    //
    //         var service = new LoanService(bookRepository, loanRepository, notificationService);
    //         service.createLoan("alice", 1L, 14);
    //
    //         // 1ro: buscar libro, 2do: guardar libro (mark unavailable), 3ro: guardar préstamo
    //         var order = inOrder(bookRepository, loanRepository);
    //         order.verify(bookRepository).findById(1L);
    //         order.verify(bookRepository).save(any());
    //         order.verify(loanRepository).save(any());
    //     }
    //
    //     @Test
    //     @DisplayName("throws when book not available")
    //     void throwsWhenUnavailable() {
    //         var book = new Book(1L, "Clean Code");
    //         book.setAvailable(false);
    //         when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    //
    //         var service = new LoanService(bookRepository, loanRepository, notificationService);
    //
    //         assertThatThrownBy(() -> service.createLoan("alice", 1L, 14))
    //             .isInstanceOf(IllegalStateException.class)
    //             .hasMessage("Book not available");
    //
    //         // Nunca se guardó nada porque fallo temprano
    //         verify(loanRepository, never()).save(any());
    //         verify(bookRepository, never()).save(any());
    //     }
    // }

    // ============================================
    // PASO 4: doAnswer — Efectos secundarios en void
    // ============================================
    // Descomenta:

    // @Nested
    // @DisplayName("PASO 4: doAnswer — simulate side effects")
    // class Step4 {
    //
    //     @Test
    //     @DisplayName("doAnswer simulates DB assigning an ID")
    //     void simulatesDbId() {
    //         var book = new Book(1L, "Clean Code");
    //         when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    //
    //         // Simular que la DB asigna un ID al guardar
    //         when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
    //             Loan loan = invocation.getArgument(0);
    //             loan.setId(42L); // la DB asignó ID=42
    //             return loan;
    //         });
    //
    //         var service = new LoanService(bookRepository, loanRepository, notificationService);
    //         var result = service.createLoan("alice", 1L, 14);
    //
    //         assertThat(result.getId()).isEqualTo(42L);
    //         assertThat(result.getUsername()).isEqualTo("alice");
    //     }
    //
    //     @Test
    //     @DisplayName("doThrow on void method causes test to handle it")
    //     void doThrowOnVoidMethod() {
    //         // doThrow — para configurar excepciones en métodos void
    //         // notificationService.sendLoanConfirmation() es void
    //         doThrow(new RuntimeException("SMTP server down"))
    //             .when(notificationService).sendLoanConfirmation(anyString(), anyString());
    //
    //         // En un sistema robusto, el servicio debería manejar este error
    //         // Por ahora solo verificamos que la excepción se lanza correctamente
    //         assertThatThrownBy(() ->
    //             notificationService.sendLoanConfirmation("alice@example.com", "Clean Code"))
    //             .isInstanceOf(RuntimeException.class)
    //             .hasMessage("SMTP server down");
    //     }
    // }
}
