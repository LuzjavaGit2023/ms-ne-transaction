package pe.com.app.transaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.app.transaction.common.config.TransactionType;
import pe.com.app.transaction.common.mapper.TransactionMapper;
import pe.com.app.transaction.controller.request.ConsumptionRequest;
import pe.com.app.transaction.controller.request.PaymentRequest;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import pe.com.app.transaction.repository.TransactionRepository;
import pe.com.app.transaction.controller.request.DepositRequest;
import pe.com.app.transaction.controller.request.WithdrawalRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: TransactionServiceImpl <br/>
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
@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public Mono<TransactionResponse> saveWithdrawal(String serviceId, WithdrawalRequest request) {
        log.info("saveWithdrawal : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Withdrawal))
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> savePayment(String serviceId, PaymentRequest request) {
        log.info("savePayment : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Payment))
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> saveConsumption(String serviceId, ConsumptionRequest request) {
        log.info("saveConsumption : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Consumption))
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> saveDeposit(String serviceId, DepositRequest request) {
        log.info("saveDeposit : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Deposit))
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Flux<TransactionDataResponse> getTransactionsByServiceId(String serviceId) {
        log.info("getTransactionsByServiceId : execute, service {}", serviceId);
        return repository.findByServiceId(serviceId)
                .map(transactionEntity -> TransactionMapper.buildTransactionDataResponse(transactionEntity))
                .log();
    }
}
