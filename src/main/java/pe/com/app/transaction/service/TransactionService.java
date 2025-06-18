package pe.com.app.transaction.service;

import pe.com.app.transaction.controller.request.CommissionRequest;
import pe.com.app.transaction.controller.request.ConsumptionRequest;
import pe.com.app.transaction.controller.request.DepositRequest;
import pe.com.app.transaction.controller.request.PaymentRequest;
import pe.com.app.transaction.controller.request.WithdrawalRequest;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Interface</b>: TransactionService <br/>
 * <b>Copyright</b>: 2025 Tu Banco - Celula <br/>
 * .
 *
 * @author 2025 Tu Banco - Peru <br/>
 * <u>Service Provider</u>: Tu Banco <br/>
 * <u>Changes:</u><br/>
 * <ul>
 * <li>
 * May 10, 2025 Creación de Clase.
 * </li>
 * </ul>
 */
public interface TransactionService {
    Mono<TransactionResponse> saveCommission(String serviceId, CommissionRequest request);
    Mono<TransactionResponse> saveWithdrawal(String serviceId, WithdrawalRequest request);

    Mono<TransactionResponse> savePayment(String serviceId, PaymentRequest request);

    Mono<TransactionResponse> saveConsumption(String serviceId, ConsumptionRequest request);

    Mono<TransactionResponse> saveDeposit(String serviceId, DepositRequest request);

    Flux<TransactionDataResponse> getTransactionsByServiceId(String serviceId);
    Flux<TransactionDataResponse> getTransactionsAll();
    Flux<TransactionDataResponse> getAllCommission();

}
