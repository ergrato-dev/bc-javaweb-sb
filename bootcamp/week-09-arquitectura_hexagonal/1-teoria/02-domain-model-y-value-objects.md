# Domain Model y Value Objects

## 🎯 Objetivos
- Modelar el dominio con entidades ricas y Value Objects
- Aplicar principios DDD (Domain-Driven Design) básicos
- Separar domain logic de infrastructure logic

---

## 1. Entidad del Dominio vs Entidad JPA

En arquitectura hexagonal, la entidad de dominio y la entidad JPA son **clases distintas**:

```java
// ✅ Entidad de dominio — sin anotaciones de framework
// Tiene comportamiento rico + invariantes de negocio
public class Account {
    private final AccountId id;
    private Money balance;
    private AccountStatus status;
    private final List<Transaction> transactions = new ArrayList<>();

    public TransactionId deposit(Money amount, String description) {
        if (status == AccountStatus.FROZEN) throw new AccountFrozenException(id);
        balance = balance.add(amount);
        var tx = new Transaction(TransactionType.DEPOSIT, amount, description);
        transactions.add(tx);
        return tx.getId();
    }

    public TransactionId withdraw(Money amount, String description) {
        if (status == AccountStatus.FROZEN) throw new AccountFrozenException(id);
        if (balance.isLessThan(amount)) throw new InsufficientFundsException(id, amount);
        balance = balance.subtract(amount);
        var tx = new Transaction(TransactionType.WITHDRAWAL, amount, description);
        transactions.add(tx);
        return tx.getId();
    }
}

// ✅ Entidad JPA — solo mapeo a BD
// En el paquete infrastructure/adapter/out/persistence
@Entity @Table(name = "accounts")
class AccountJpaEntity {
    @Id private String id;
    @Column private BigDecimal balance;
    @Column private String currency;
    @Column private String status;
}
```

---

## 2. Value Objects

Objetos **inmutables** definidos por sus atributos — sin identidad propia.

```java
// ✅ Value Object con Java Record (inmutable por defecto)
public record Money(BigDecimal amount, String currency) {

    // Validación en compact constructor
    public Money {
        Objects.requireNonNull(amount, "Amount is required");
        Objects.requireNonNull(currency, "Currency is required");
        if (amount.scale() > 2) throw new IllegalArgumentException("Max 2 decimal places");
        if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount cannot be negative");
    }

    public Money add(Money other) {
        requireSameCurrency(other);
        return new Money(amount.add(other.amount), currency);
    }

    public Money subtract(Money other) {
        requireSameCurrency(other);
        var result = amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Result would be negative");
        return new Money(result, currency);
    }

    public boolean isLessThan(Money other) {
        requireSameCurrency(other);
        return amount.compareTo(other.amount) < 0;
    }

    private void requireSameCurrency(Money other) {
        if (!currency.equals(other.currency)) throw new CurrencyMismatchException(currency, other.currency);
    }

    // Factories
    public static Money of(double amount, String currency) {
        return new Money(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP), currency);
    }

    public static Money zero(String currency) { return new Money(BigDecimal.ZERO, currency); }
}

// AccountId — evita confundir IDs entre entidades
public record AccountId(String value) {
    public AccountId { Objects.requireNonNull(value, "AccountId cannot be null"); }
    public static AccountId generate() { return new AccountId(UUID.randomUUID().toString()); }
}
```

---

## 3. Casos de Uso (Use Cases)

Los casos de uso son **comandos** que expresan intenciones del usuario:

```java
// Puerto de entrada — interfaz en application/port/in/
public interface TransferMoneyUseCase {
    record TransferCommand(AccountId sourceId, AccountId targetId, Money amount) {}
    void transfer(TransferCommand command);
}

// Implementación del Use Case — en application/usecase/
@Service
class TransferMoneyService implements TransferMoneyUseCase {

    private final LoadAccountPort loadAccountPort;    // puerto de salida
    private final SaveAccountPort saveAccountPort;

    @Override
    @Transactional
    public void transfer(TransferCommand command) {
        var source = loadAccountPort.load(command.sourceId());
        var target = loadAccountPort.load(command.targetId());

        source.withdraw(command.amount(), "Transfer to " + command.targetId().value());
        target.deposit(command.amount(), "Transfer from " + command.sourceId().value());

        saveAccountPort.save(source);
        saveAccountPort.save(target);
    }
}
```

---

## 4. Adaptador de Persistencia (Salida)

```java
// Adaptador de salida — implementa los puertos del dominio
@Component
class AccountPersistenceAdapter implements LoadAccountPort, SaveAccountPort {

    private final AccountJpaEntityRepository jpaRepository;

    @Override
    public Account load(AccountId id) {
        return jpaRepository.findById(id.value())
            .map(this::toDomain)
            .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public void save(Account account) {
        jpaRepository.save(toJpaEntity(account));
    }

    private Account toDomain(AccountJpaEntity entity) {
        return new Account(
            new AccountId(entity.getId()),
            new Money(entity.getBalance(), entity.getCurrency()),
            AccountStatus.valueOf(entity.getStatus())
        );
    }
}
```

---

## ✅ Checklist

- [ ] Value Objects son Records (inmutables) con validación en el constructor
- [ ] Entidades de dominio tienen comportamiento rico (no solo getters/setters)
- [ ] Use Cases implementan un único puerto de entrada
- [ ] Adaptadores traducen entre modelo de dominio y modelo de infraestructura
