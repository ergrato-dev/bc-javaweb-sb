# Implementar Hexagonal en Spring Boot

## 🎯 Objetivos
- Estructurar un proyecto Spring Boot con arquitectura hexagonal
- Conectar dominios, puertos y adaptadores con Spring DI
- Testing por capa en arquitectura hexagonal

---

## 1. Configuración de Spring en Hexagonal

La única forma de Spring que entra al dominio es a través de `@Transactional` en el Use Case (aceptable) o en el adaptador de persistencia:

```java
// application/config/BeanConfig.java — wiring manual de puertos
@Configuration
class BeanConfig {

    @Bean
    TransferMoneyUseCase transferMoneyUseCase(
            LoadAccountPort loadAccountPort,
            SaveAccountPort saveAccountPort) {
        // El Use Case no es @Service si queremos dominio puro
        return new TransferMoneyService(loadAccountPort, saveAccountPort);
    }
}

// Alternativa: aceptar @Service en el UseCase (pragmático)
@Service
class TransferMoneyService implements TransferMoneyUseCase { ... }
```

---

## 2. Adaptador de entrada (Web)

```java
// infrastructure/adapter/in/web/TransferController.java
@RestController
@RequestMapping("/api/accounts")
class TransferController {

    private final TransferMoneyUseCase transferMoneyUseCase;

    TransferController(TransferMoneyUseCase transferMoneyUseCase) {
        this.transferMoneyUseCase = transferMoneyUseCase;
    }

    @PostMapping("/{sourceId}/transfers")
    ResponseEntity<Void> transfer(
            @PathVariable String sourceId,
            @Valid @RequestBody TransferRequest request) {

        var command = new TransferMoneyUseCase.TransferCommand(
            new AccountId(sourceId),
            new AccountId(request.targetAccountId()),
            Money.of(request.amount(), request.currency()));

        transferMoneyUseCase.transfer(command);
        return ResponseEntity.noContent().build();
    }
}

record TransferRequest(
    @NotBlank String targetAccountId,
    @Positive double amount,
    @NotBlank String currency
) {}
```

---

## 3. Mapeador entre dominio y JPA

```java
// En el paquete del adaptador de persistencia
@Component
class AccountMapper {

    Account toDomain(AccountJpaEntity entity) {
        return Account.reconstitute(
            new AccountId(entity.getId()),
            new Money(entity.getBalance(), entity.getCurrency()),
            AccountStatus.valueOf(entity.getStatus()),
            entity.getCreatedAt()
        );
    }

    AccountJpaEntity toJpaEntity(Account account) {
        var entity = new AccountJpaEntity();
        entity.setId(account.getId().value());
        entity.setBalance(account.getBalance().amount());
        entity.setCurrency(account.getBalance().currency());
        entity.setStatus(account.getStatus().name());
        return entity;
    }
}
```

---

## 4. Testing en Hexagonal

### Test del dominio puro (sin Spring)

```java
class AccountTest {  // sin @SpringBootTest ni @ExtendWith

    @Test
    void deposit_shouldIncreaseBalance() {
        var account = new Account(AccountId.generate(),
                                  Money.of(100, "USD"),
                                  AccountStatus.ACTIVE);
        account.deposit(Money.of(50, "USD"), "salary");

        assertThat(account.getBalance()).isEqualTo(Money.of(150, "USD"));
    }

    @Test
    void withdraw_shouldThrow_whenInsufficientFunds() {
        var account = new Account(AccountId.generate(),
                                  Money.of(50, "USD"),
                                  AccountStatus.ACTIVE);
        assertThatThrownBy(() -> account.withdraw(Money.of(100, "USD"), "payment"))
            .isInstanceOf(InsufficientFundsException.class);
    }
}
```

### Test del Use Case (con mocks de puertos)

```java
@ExtendWith(MockitoExtension.class)
class TransferMoneyServiceTest {

    @Mock LoadAccountPort loadAccountPort;
    @Mock SaveAccountPort saveAccountPort;
    @InjectMocks TransferMoneyService transferMoneyService;

    @Test
    void transfer_shouldDebitSourceAndCreditTarget() {
        var source = givenAccountWithBalance(Money.of(500, "USD"));
        var target = givenAccountWithBalance(Money.of(100, "USD"));

        when(loadAccountPort.load(source.getId())).thenReturn(source);
        when(loadAccountPort.load(target.getId())).thenReturn(target);

        transferMoneyService.transfer(new TransferCommand(
            source.getId(), target.getId(), Money.of(200, "USD")));

        assertThat(source.getBalance()).isEqualTo(Money.of(300, "USD"));
        assertThat(target.getBalance()).isEqualTo(Money.of(300, "USD"));
        verify(saveAccountPort, times(2)).save(any());
    }
}
```

---

## ✅ Checklist

- [ ] `@Bean` configuration wires ports to adapters in `BeanConfig`
- [ ] Domain model tests run without Spring context (pure JUnit)
- [ ] Use Case tests use `@ExtendWith(MockitoExtension.class)` with mock ports
- [ ] Web adapter tests use `@WebMvcTest` with mock use case
