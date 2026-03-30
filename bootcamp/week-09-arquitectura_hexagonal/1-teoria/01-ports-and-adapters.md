# Arquitectura Hexagonal — Ports & Adapters

## 🎯 Objetivos
- Entender la arquitectura hexagonal y su motivación
- Identificar el Dominio, Puertos y Adaptadores
- Comparar arquitectura en capas vs hexagonal

---

## 1. ¿Qué es la Arquitectura Hexagonal?

Propuesta por Alistair Cockburn ("Ports and Adapters"). El objetivo: **aislar el dominio de cualquier tecnología**.

```
        [ REST Controller ]  [ CLI ]  [ Test Driver ]
               │                │           │
               └────────────────┼───────────┘
                          INPUT PORT
                    ┌─────────────────────┐
                    │   APPLICATION CORE  │
                    │  (Domain + UseCases)│
                    └─────────────────────┘
                         OUTPUT PORT
               ┌──────────────┼────────────┐
               │              │            │
         [ JpaRepo ]    [ EmailSvc ]  [ CacheAdapter ]
```

**Regla fundamental:** El dominio NO depende de frameworks, bases de datos ni HTTP. Todos dependen del dominio.

---

## 2. Las tres zonas

### Zona 1: Dominio
- Entidades, Value Objects, Domain Services
- Lógica de negocio pura — sin Spring, sin JPA, sin HTTP
- Se expresa en lenguaje del negocio (DDD)

```java
// Dominio puro — sin anotaciones Spring/JPA
public record Money(BigDecimal amount, Currency currency) {
    public Money {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount cannot be negative");
    }
    public Money add(Money other) {
        if (!currency.equals(other.currency)) throw new CurrencyMismatchException();
        return new Money(amount.add(other.amount), currency);
    }
}

public class Account {
    private AccountId id;
    private Money balance;

    public void deposit(Money amount) {
        this.balance = balance.add(amount);
    }
    public void withdraw(Money amount) {
        if (balance.amount().compareTo(amount.amount()) < 0)
            throw new InsufficientFundsException(id);
        this.balance = new Money(balance.amount().subtract(amount.amount()), balance.currency());
    }
}
```

### Zona 2: Puertos (Interfaces)

Los puertos son contratos — sin implementación:

```java
// Puerto de entrada (input port) — lo que el exterior puede pedir
public interface TransferMoneyUseCase {
    TransferResult transfer(TransferCommand command);
}

// Puerto de salida (output port) — lo que el dominio necesita
public interface AccountRepository {
    Account findById(AccountId id);
    void save(Account account);
}
```

### Zona 3: Adaptadores

Implementan los puertos — con Spring, JPA, etc.:

```java
// Adaptador primario (left) — expone el caso de uso via HTTP
@RestController
@RequestMapping("/api/transfers")
class TransferController {
    private final TransferMoneyUseCase transferUseCase;
    // ... implementa el puerto de entrada
}

// Adaptador secundario (right) — implementa el puerto de salida
@Repository
class AccountJpaAdapter implements AccountRepository {
    private final AccountJpaEntityRepository jpaRepository;
    // ... mapea entre JPA Entity y Domain Account
}
```

---

## 3. Estructura de paquetes hexagonal

```
com.bootcamp.banking/
├── domain/
│   ├── Account.java            # Entidad del dominio
│   ├── Money.java              # Value Object
│   └── AccountId.java         # Value Object ID
├── application/
│   ├── port/
│   │   ├── in/                 # Puertos de entrada (Use Cases)
│   │   │   └── TransferMoneyUseCase.java
│   │   └── out/                # Puertos de salida
│   │       └── AccountRepository.java
│   └── usecase/
│       └── TransferMoneyService.java  # Implementa el puerto de entrada
└── infrastructure/
    ├── adapter/
    │   ├── in/
    │   │   └── web/
    │   │       └── TransferController.java
    │   └── out/
    │       └── persistence/
    │           ├── AccountJpaAdapter.java
    │           └── AccountJpaEntity.java
    └── config/
        └── BeanConfig.java
```

---

## 4. Capas vs Hexagonal

| Aspecto | Capas | Hexagonal |
|---|---|---|
| Dependencias | Controller → Service → Repo | Todo → Dominio |
| Testabilidad | Necesita mocking de repos | Dominio testeable sin Spring |
| Flexibilidad | Cambiar DB requiere tocar Service | Solo cambiar el adaptador de salida |
| Complejidad | Menor | Mayor (más abstracciones) |
| Cuándo usar | Apps CRUD estándar | Apps con lógica de negocio compleja |

---

## ✅ Checklist

- [ ] El dominio NO importa ningún framework (sin `@Component`, sin `@Entity`)
- [ ] Los puertos son interfaces en el paquete `application/port/`
- [ ] Los adaptadores implementan los puertos en `infrastructure/adapter/`
- [ ] Los métodos del dominio expresan lenguaje de negocio, no CRUD
