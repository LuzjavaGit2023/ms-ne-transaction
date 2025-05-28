package pe.com.app.transaction.common.mapper;

import java.time.LocalDateTime;
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

public class TransactionMapper {

    public static TransactionEntity buildTransactionEntityNew(PaymentRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type);
    }

    public static TransactionEntity buildTransactionEntityNew(ConsumptionRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type);
    }

    public static TransactionEntity buildTransactionEntityNew(DepositRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type);
    }

    public static TransactionEntity buildTransactionEntityNew(WithdrawalRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type);
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
