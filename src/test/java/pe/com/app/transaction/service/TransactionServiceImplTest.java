package pe.com.app.transaction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.app.transaction.common.config.Currency;
import pe.com.app.transaction.common.config.TransactionOrigin;
import pe.com.app.transaction.common.config.TransactionStatus;
import pe.com.app.transaction.common.config.TransactionType;
import pe.com.app.transaction.controller.request.*;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import pe.com.app.transaction.model.persistence.TransactionEntity;
import pe.com.app.transaction.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl service;

    @Mock
    private TransactionRepository repository;

    private CommissionRequest commission;
    private WithdrawalRequest withdrawal;
    private PaymentRequest payment;
    private ConsumptionRequest consumption;
    private DepositRequest deposit;
    private TransactionEntity entity;

    @BeforeEach
    void setup() {
        entity = buildTransactionEntity(TransactionType.Commission);
        commission = CommissionRequest.builder().amount(-0.2d).currency(Currency.PEN).build();
        withdrawal = WithdrawalRequest.builder().amount(-20d).currency(Currency.PEN).build();
        payment = PaymentRequest.builder().amount(20d).currency(Currency.PEN).build();
        consumption = ConsumptionRequest.builder().amount(-20d).currency(Currency.PEN).build();
        deposit = DepositRequest.builder().amount(20d).currency(Currency.PEN).build();
    }

    @Test
    void givenCreateCommission_whenCreate_thenReturnSuccess() {
        when(repository.save(any())).thenReturn(Mono.just(buildTransactionEntity(TransactionType.Commission)));
        var result = service.saveCommission("idServ", commission);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof TransactionResponse);
                })
                .verifyComplete();
    }

    @Test
    void givenCreateWithdrawal_whenCreate_thenReturnSuccess() {
        when(repository.save(any())).thenReturn(Mono.just(buildTransactionEntity(TransactionType.Withdrawal)));
        var result = service.saveWithdrawal("idServ", withdrawal);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof TransactionResponse);
                })
                .verifyComplete();
    }

    @Test
    void givenCreatePayment_whenCreate_thenReturnSuccess() {
        when(repository.save(any())).thenReturn(Mono.just(buildTransactionEntity(TransactionType.Payment)));
        var result = service.savePayment("idServ", payment);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof TransactionResponse);
                })
                .verifyComplete();
    }

    @Test
    void givenCreateConsumption_whenCreate_thenReturnSuccess() {
        when(repository.save(any())).thenReturn(Mono.just(buildTransactionEntity(TransactionType.Consumption)));
        var result = service.saveConsumption("idServ", consumption);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof TransactionResponse);
                })
                .verifyComplete();
    }

    @Test
    void givenCreateDeposit_whenCreate_thenReturnSuccess() {
        when(repository.save(any())).thenReturn(Mono.just(buildTransactionEntity(TransactionType.Deposit)));
        var result = service.saveDeposit("idServ", deposit);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertTrue(response instanceof TransactionResponse);
                })
                .verifyComplete();
    }

    @Test
    void givenGetByServiceRequest_whenGetById_thenReturnSuccess() {
        var list = List.of( entity,
                buildTransactionEntity(TransactionType.Commission),
                buildTransactionEntity(TransactionType.Withdrawal),
                buildTransactionEntity(TransactionType.Deposit),
                buildTransactionEntity(TransactionType.Deposit),
                buildTransactionEntity(TransactionType.Deposit),
                buildTransactionEntity(TransactionType.Deposit)
        );
        when(repository.findByServiceId(any())).thenReturn(Flux.fromIterable(list));
        var result = service.getTransactionsByServiceId("idServ");
        StepVerifier.create(result.collectList())
                .assertNext(listResult -> {
                    assertEquals(7, listResult.size());
                    listResult.forEach(element -> assertTrue(element instanceof TransactionDataResponse));
                })
                .verifyComplete();
    }

    @Test
    void givenGetAllRequest_whenGetAll_thenReturnSuccess() {
        var list = List.of( entity,
                buildTransactionEntity(TransactionType.Commission),
                buildTransactionEntity(TransactionType.Withdrawal),
                buildTransactionEntity(TransactionType.Deposit),
                buildTransactionEntity(TransactionType.Deposit),
                buildTransactionEntity(TransactionType.Consumption),
                buildTransactionEntity(TransactionType.Consumption),
                buildTransactionEntity(TransactionType.Payment),
                buildTransactionEntity(TransactionType.Payment)
        );
        when(repository.findAll()).thenReturn(Flux.fromIterable(list));
        var result = service.getTransactionsAll();
        StepVerifier.create(result.collectList())
                .assertNext(listResult -> {
                    assertEquals(9, listResult.size());
                    listResult.forEach(element -> assertTrue(element instanceof TransactionDataResponse));
                })
                .verifyComplete();
    }

    @Test
    void givenGetAllCommissionsRequest_whenGetAll_thenReturnSuccess() {
        var list = List.of(
                buildTransactionEntity(TransactionType.Commission),
                buildTransactionEntity(TransactionType.Commission),
                buildTransactionEntity(TransactionType.Commission),
                buildTransactionEntity(TransactionType.Commission),
                buildTransactionEntity(TransactionType.Commission),
                buildTransactionEntity(TransactionType.Commission)
        );
        when(repository.findByType(any())).thenReturn(Flux.fromIterable(list));
        var result = service.getAllCommission();
        StepVerifier.create(result.collectList())
                .assertNext(listResult -> {
                    assertEquals(6, listResult.size());
                    listResult.forEach(element -> assertTrue(element instanceof TransactionDataResponse));
                })
                .verifyComplete();
    }

    private TransactionEntity buildTransactionEntity(TransactionType type) {
        double amount;
        switch (type) {
            case Commission:
                amount = getRandomMin() * (-1); break;
            case Consumption:
            case Withdrawal:
                amount = getRandom() * (-1); break;
            default:
                amount = getRandom();
        }

        return TransactionEntity.builder()
                .id(String.valueOf(getRandom()))
                .serviceId(String.valueOf(getRandom()))
                .entityClient(String.valueOf(getRandom()))
                .transactionDate(LocalDateTime.now())
                .type(type)
                .origin(TransactionOrigin.ACCOUNT)
                .currency(Currency.PEN)
                .amount(amount)
                .entityClient("Trx today")
                .status(TransactionStatus.ACTIVE)
                .build();
    }

    private static double getRandomMin() {
        return getRandom(1, 2) - 0.6;
    }

    private static int getRandom() {
        return getRandom(100, 200);
    }

    private static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
