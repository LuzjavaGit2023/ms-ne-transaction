package pe.com.app.transaction.common.mapper;

import pe.com.app.transaction.common.config.Currency;
import pe.com.app.transaction.common.config.TransactionStatus;
import pe.com.app.transaction.common.config.TransactionType;
import pe.com.app.transaction.controller.request.ConsumptionRequest;
import pe.com.app.transaction.controller.request.DepositRequest;
import pe.com.app.transaction.controller.request.PaymentRequest;
import pe.com.app.transaction.controller.request.WithdrawalRequest;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import pe.com.app.transaction.model.persistence.TransactionEntity;

import java.time.LocalDateTime;

public class TransactionMapper {

    public static TransactionEntity buildTransactionEntityNew(PaymentRequest request, TransactionType type){
        return buildTransactionEntityBaseActive( request.getServiceId(), request.getAmount(), request.getCurrency(), type);
    }

    public static TransactionEntity buildTransactionEntityNew(ConsumptionRequest request, TransactionType type){
        return buildTransactionEntityBaseActive( request.getServiceId(), request.getAmount(), request.getCurrency(), type);
    }

    public static TransactionEntity buildTransactionEntityNew(DepositRequest request, TransactionType type){
        return buildTransactionEntityBaseActive( request.getServiceId(), request.getAmount(), request.getCurrency(), type);
    }

    public static TransactionEntity buildTransactionEntityNew(WithdrawalRequest request, TransactionType type){
        return buildTransactionEntityBaseActive( request.getServiceId(), request.getAmount(), request.getCurrency(), type);
    }

    private static TransactionEntity buildTransactionEntityBaseActive(
            String serviceId,
            Double amount,
            Currency currency,
            TransactionType type
    ) {
        return TransactionEntity.builder()
                .serviceId(serviceId)
                .amount(amount)
                .currency(currency)
                .type(type)
                .transactionDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.ACTIVE)
                .build();
    }

    public static TransactionResponse buildWithdrawalResponse(TransactionEntity transactionEntity) {
        return TransactionResponse.builder()
                .id(transactionEntity.getId())
                .build();
    }

    public static TransactionDataResponse buildTransactionDataResponse(TransactionEntity trx) {
        return TransactionDataResponse.builder()
                .id(trx.getId())
                .serviceId(trx.getServiceId())
                .amount(trx.getAmount())
                .currency(trx.getCurrency())
                .type(trx.getType())
                .status(trx.getStatus())
                .transactionDate(trx.getTransactionDate())
                .build();
    }
}
