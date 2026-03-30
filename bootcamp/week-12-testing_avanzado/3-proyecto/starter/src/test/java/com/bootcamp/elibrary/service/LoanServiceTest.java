package com.bootcamp.elibrary.service;

import com.bootcamp.elibrary.domain.Book;
import com.bootcamp.elibrary.domain.BookStatus;
import com.bootcamp.elibrary.domain.Loan;
import com.bootcamp.elibrary.domain.LoanStatus;
import com.bootcamp.elibrary.dto.Dtos.*;
import com.bootcamp.elibrary.exception.BookNotFoundException;
import com.bootcamp.elibrary.exception.LoanNotFoundException;
import com.bootcamp.elibrary.repository.BookRepository;
import com.bootcamp.elibrary.repository.LoanRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * LoanServiceTest — Escribe aquí los tests para LoanService.
 *
 * REGLAS DE NEGOCIO A TESTEAR:
 *   1. Un usuario puede tener máximo 3 préstamos activos
 *   2. El libro debe tener copias disponibles
 *   3. Solo el prestatario puede devolver un libro
 *   4. No se puede devolver un préstamo ya devuelto
 *
 * INSTRUCCIONES:
 * 1. Implementa cada método de test reemplazando el comentario // TODO
 * 2. Usa verify() para confirmar el orden correcto de operaciones
 * 3. Usa ArgumentCaptor para verificar los datos del Loan guardado
 * 4. Ejecuta los tests con: mvn test
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("LoanService")
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LoanService loanService;

    @Captor
    private ArgumentCaptor<Loan> loanCaptor;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    // --- Helpers ---
    private Book availableBook() {
        return new Book(1L, "Clean Code", "9780132350884", "Robert Martin",
                       BigDecimal.valueOf(29.99), 2);
    }

    private Book unavailableBook() {
        var book = new Book(2L, "Refactoring", "9780201485677", "Martin Fowler",
                           BigDecimal.valueOf(39.99), 1);
        book.checkOut(); // now availableCopies = 0
        return book;
    }

    // ============================================
    // createLoan()
    // ============================================

    @Nested
    @DisplayName("createLoan()")
    class CreateLoan {

        @Test
        @DisplayName("creates loan successfully")
        void createsLoan() {
            // TODO: Implementar
            // 1. Stubbear countByUsernameAndStatus("alice", ACTIVE) → 0L
            // 2. Stubbear bookRepository.findById(1L) → Optional.of(availableBook())
            // 3. Stubbear bookRepository.save(any()) → retornar argumento
            // 4. Stubbear loanRepository.save(any()) → retornar argumento (thenAnswer)
            // 5. Llamar loanService.createLoan("alice", new LoanCreateRequest(1L, 14))
            // 6. Verificar: username "alice", status ACTIVE, dueDate = hoy + 14 días
        }

        @Test
        @DisplayName("captures correct loan fields when creating")
        void capturesLoanFields() {
            // TODO: Implementar — usar loanCaptor
            // 1. Setup stubs (igual que createsLoan)
            // 2. Ejecutar createLoan
            // 3. verify(loanRepository).save(loanCaptor.capture())
            // 4. Verificar en el Loan capturado:
            //    - username == "alice"
            //    - book.getId() == 1L
            //    - status == LoanStatus.ACTIVE
            //    - dueDate == LocalDate.now().plusDays(14)
            //    - returnedAt == null
        }

        @Test
        @DisplayName("verifies order: check book → update book → save loan")
        void verifiesOperationOrder() {
            // TODO: Implementar — usar inOrder
            // 1. Setup stubs
            // 2. Ejecutar createLoan
            // 3. Crear: var order = inOrder(bookRepository, loanRepository)
            // 4. Verificar orden:
            //    - order.verify(bookRepository).findById(1L)
            //    - order.verify(bookRepository).save(any())    ← marcar como no disponible
            //    - order.verify(loanRepository).save(any())    ← guardar préstamo
        }

        @Test
        @DisplayName("throws when user has 3 active loans")
        void throwsAtMaxActiveLoans() {
            // TODO: Implementar
            // 1. Stubbear countByUsernameAndStatus("alice", ACTIVE) → 3L
            // 2. Verificar que createLoan lanza IllegalStateException
            // 3. Verificar que el mensaje contiene "3" o "maximum"
            // 4. Verificar que bookRepository NUNCA fue consultado (no había necesidad)
        }

        @Test
        @DisplayName("throws when book is not found")
        void throwsWhenBookNotFound() {
            // TODO: Implementar
            // 1. Stubbear count → 0L
            // 2. Stubbear bookRepository.findById(99L) → Optional.empty()
            // 3. Verificar que lanza BookNotFoundException con "99" en el mensaje
        }

        @Test
        @DisplayName("throws when book has no available copies")
        void throwsWhenBookUnavailable() {
            // TODO: Implementar
            // 1. Stubbear count → 0L
            // 2. Stubbear findById(2L) → Optional.of(unavailableBook())  ← sin copias
            // 3. Verificar que lanza IllegalStateException
            // 4. Verificar que loanRepository.save() nunca fue llamado
        }
    }

    // ============================================
    // returnBook()
    // ============================================

    @Nested
    @DisplayName("returnBook()")
    class ReturnBook {

        private Loan activeLoan() {
            var loan = new Loan("alice", availableBook(), 14);
            // Usamos reflection para set ID porque Loan protege el constructor JPA
            // En tests reales de integración, la DB asignaría el ID
            try {
                var field = Loan.class.getDeclaredField("id");
                field.setAccessible(true);
                field.set(loan, 10L);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return loan;
        }

        @Test
        @DisplayName("returns book successfully")
        void returnsBook() {
            // TODO: Implementar
            // 1. Crear activeLoan() con id=10
            // 2. Stubbear findByIdAndUsername(10L, "alice") → Optional.of(activeLoan)
            // 3. Stubbear loanRepository.save(any()) → retornar argumento
            // 4. Llamar loanService.returnBook("alice", 10L)
            // 5. Verificar que el resultado tiene status RETURNED y returnedAt != null
        }

        @Test
        @DisplayName("throws LoanNotFoundException when loan does not belong to user")
        void throwsWhenLoanNotFound() {
            // TODO: Implementar
            // 1. Stubbear findByIdAndUsername(99L, "bob") → Optional.empty()
            // 2. Verificar que returnBook("bob", 99L) lanza LoanNotFoundException
        }

        @Test
        @DisplayName("throws IllegalStateException when loan is already returned")
        void throwsWhenAlreadyReturned() {
            // TODO: Implementar
            // 1. Crear activeLoan, llamar returnBook() sobre él (con loan.returnBook() directamente)
            //    para que quede en estado RETURNED
            // 2. Stubbear findByIdAndUsername → Optional.of(returnedLoan)
            // 3. Verificar que loanService.returnBook() lanza IllegalStateException
        }
    }

    // ============================================
    // findByUsername()
    // ============================================

    @Nested
    @DisplayName("findByUsername()")
    class FindByUsername {

        @Test
        @DisplayName("returns all loans for user")
        void returnsLoans() {
            // TODO: Implementar
            // 1. Crear 2 Loans con username "alice"
            // 2. Stubbear findByUsername("alice") → list de 2 loans
            // 3. Llamar loanService.findByUsername("alice")
            // 4. Verificar que el resultado tiene 2 elementos
            // 5. Verificar que todos tienen username "alice" usando
            //    assertThat(result).extracting(LoanResponse::username).containsOnly("alice")
        }

        @Test
        @DisplayName("returns empty list when user has no loans")
        void returnsEmptyList() {
            // TODO: Implementar
            // 1. Stubbear findByUsername("bob") → List.of()
            // 2. Verificar que el resultado está vacío
        }
    }
}
