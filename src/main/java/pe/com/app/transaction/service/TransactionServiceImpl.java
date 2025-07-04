package pe.com.app.transaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.app.transaction.common.config.TransactionOrigin;
import pe.com.app.transaction.common.config.TransactionStatus;
import pe.com.app.transaction.common.config.TransactionType;
import pe.com.app.transaction.common.mapper.TransactionMapper;
import pe.com.app.transaction.controller.request.*;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import pe.com.app.transaction.repository.TransactionRepository;
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
    public Mono<TransactionResponse> saveCommission(String serviceId, CommissionRequest request) {
        log.info("saveCommission : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Commission))
                .flatMap(transactionEntity -> {
                    transactionEntity.setOrigin(request.getOrigin());
                    return Mono.just(transactionEntity);
                })
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> saveWithdrawal(String serviceId, WithdrawalRequest request) {
        log.info("saveWithdrawal : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Withdrawal))
                .flatMap(transactionEntity -> {
                    transactionEntity.setOrigin(TransactionOrigin.ACCOUNT);
                    return Mono.just(transactionEntity);
                })
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> savePayment(String serviceId, PaymentRequest request) {
        log.info("savePayment : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Payment))
                .flatMap(transactionEntity -> {
                    transactionEntity.setOrigin(TransactionOrigin.CREDIT);
                    return Mono.just(transactionEntity);
                })
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> saveConsumption(String serviceId, ConsumptionRequest request) {
        log.info("saveConsumption : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Consumption))
                .flatMap(transactionEntity -> {
                    transactionEntity.setOrigin(TransactionOrigin.CREDIT);
                    return Mono.just(transactionEntity);
                })
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> saveDeposit(String serviceId, DepositRequest request) {
        log.info("saveDeposit : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.Deposit))
                .flatMap(transactionEntity -> {
                    transactionEntity.setOrigin(TransactionOrigin.ACCOUNT);
                    return Mono.just(transactionEntity);
                })
                .flatMap(transactionEntity -> repository.save(transactionEntity))
                .map(transactionEntity -> TransactionMapper.buildWithdrawalResponse(transactionEntity))
                .log();
    }

    @Override
    public Mono<TransactionResponse> saveTransactionElectronicMoney(String serviceId, TransactionElectronicMoneyRequest request) {
        log.info("saveTransactionElectronicMoney : execute, service {}, request {}", serviceId, request);
        request.setServiceId(serviceId);
        return Mono.just(TransactionMapper.buildTransactionEntityNew(request, TransactionType.P2P))
                .flatMap(transactionEntity -> {
                    transactionEntity.setOrigin(TransactionOrigin.ELECTRONIC_MONEY);
                    transactionEntity.setStatus(TransactionStatus.OFFERED);
                    return Mono.just(transactionEntity);
                })
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

    @Override
    public Flux<TransactionDataResponse> getTransactionsAll() {
        log.info("getTransactionsAll : execute");
        return repository.findAll()
                .map(transactionEntity -> TransactionMapper.buildTransactionDataResponse(transactionEntity));
    }

    @Override
    public Flux<TransactionDataResponse> getAllCommission() {
        log.info("getAllCommission : execute");
        return repository.findByType(TransactionType.Commission)
                .map(transactionEntity -> TransactionMapper.buildTransactionDataResponse(transactionEntity));
    }
}
