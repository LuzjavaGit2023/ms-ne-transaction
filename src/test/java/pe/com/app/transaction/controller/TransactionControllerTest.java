package pe.com.app.transaction.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import pe.com.app.transaction.controller.request.*;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import pe.com.app.transaction.service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TransactionService service;

    private CommissionRequest requestCommission;

    private TransactionResponse transactionResponse;

    private WithdrawalRequest withdrawalRequest;
    private ConsumptionRequest consumptionRequest;

    private PaymentRequest paymentRequest;
    private DepositRequest depositRequest;

    private TransactionDataResponse consumptionResponse;

    private TransactionDataResponse commissionResponse;

    @BeforeEach
    void setup() {
        transactionResponse = TransactionResponse.builder().id("100").build();
        requestCommission = CommissionRequest.builder().amount(-0.3d).build();
        withdrawalRequest = WithdrawalRequest.builder().amount(-100d).build();
        consumptionRequest = ConsumptionRequest.builder().amount(-100d).build();
        paymentRequest = PaymentRequest.builder().amount(100d).build();
        depositRequest = DepositRequest.builder().amount(100d).build();
        consumptionResponse = TransactionDataResponse.builder().amount(-50d).build();
        commissionResponse = TransactionDataResponse.builder().amount(-1.5d).build();
    }

    @Test
    void givenCreateCommission_whenCreate_thenReturnSuccess() {
        when(service.saveCommission(any(), any())).thenReturn(Mono.just(transactionResponse));
        webTestClient.post()
                .uri("/transactions/{serviceId}/commission", "idService")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestCommission)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void givenCreateWithdrawal_whenCreate_thenReturnSuccess() {
        when(service.saveWithdrawal(any(), any())).thenReturn(Mono.just(transactionResponse));
        webTestClient.post()
                .uri("/transactions/{serviceId}/withdrawal", "idService")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(withdrawalRequest)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void givenCreatePayment_whenCreate_thenReturnSuccess() {
        when(service.saveConsumption(any(), any())).thenReturn(Mono.just(transactionResponse));
        webTestClient.post()
                .uri("/transactions/{serviceId}/consumption", "idService")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(consumptionRequest)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void givenCreateConsumption_whenCreate_thenReturnSuccess() {
        when(service.savePayment(any(), any())).thenReturn(Mono.just(transactionResponse));
        webTestClient.post()
                .uri("/transactions/{serviceId}/payment", "idService")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(paymentRequest)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void givenCreateDeposit_whenCreate_thenReturnSuccess() {
        when(service.saveConsumption(any(), any())).thenReturn(Mono.just(transactionResponse));
        webTestClient.post()
                .uri("/transactions/{serviceId}/deposit", "idService")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(depositRequest)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void givenGetByServiceRequest_whenGetById_thenReturnSuccess() {
        var list = List.of(consumptionResponse);
        when(service.getTransactionsByServiceId(any())).thenReturn(Flux.fromIterable(list));
        webTestClient.get()
                .uri("/transactions/{serviceId}", "idService")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TransactionDataResponse.class)
                .value(response -> {
                    Assertions.assertTrue(!response.isEmpty());
                });
    }

    @Test
    void givenGetAllRequest_whenGetAll_thenReturnSuccess() {
        var list = List.of(consumptionResponse);
        when(service.getTransactionsAll()).thenReturn(Flux.fromIterable(list));
        webTestClient.get()
                .uri("/transactions")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TransactionDataResponse.class)
                .value(response -> {
                    Assertions.assertTrue(!response.isEmpty());
                });
    }

    @Test
    void givenGetAllCommissionsRequest_whenGetAll_thenReturnSuccess() {
        var list = List.of(commissionResponse);
        when(service.getAllCommission()).thenReturn(Flux.fromIterable(list));
        webTestClient.get()
                .uri("/transactions/commission", "idService")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TransactionDataResponse.class)
                .value(response -> {
                    Assertions.assertTrue(!response.isEmpty());
                });
    }
}
