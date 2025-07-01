package pe.com.app.transaction.common.mapper;

import java.time.LocalDateTime;
import pe.com.app.transaction.common.config.Currency;
import pe.com.app.transaction.common.config.TransactionStatus;
import pe.com.app.transaction.common.config.TransactionType;
import pe.com.app.transaction.controller.request.*;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import pe.com.app.transaction.model.persistence.TransactionEntity;

public class TransactionMapper {

    public static TransactionEntity buildTransactionEntityNew(CommissionRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type
                , req.getEntityClient());
    }
    public static TransactionEntity buildTransactionEntityNew(PaymentRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type
                , req.getEntityClient());
    }

    public static TransactionEntity buildTransactionEntityNew(ConsumptionRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type
                , null);
    }

    public static TransactionEntity buildTransactionEntityNew(DepositRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type
                , req.getEntityClient());
    }

    public static TransactionEntity buildTransactionEntityNew(WithdrawalRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), req.getCurrency(), type
                , req.getEntityClient());
    }

    public static TransactionEntity buildTransactionEntityNew(TransactionElectronicMoneyRequest req, TransactionType type) {
        return buildTransactionEntityBaseActive(req.getServiceId(), req.getAmount(), Currency.BOOTCOIN, type
                , "Start transaction");
    }

    private static TransactionEntity buildTransactionEntityBaseActive(
            String serviceId,
            Double amount,
            Currency currency,
            TransactionType type,
            String entityClient
    ) {
        return TransactionEntity.builder()
                .serviceId(serviceId)
                .amount(assignValueAmount(amount, type))
                .currency(currency)
                .type(type)
                .entityClient(entityClient == null ? null : entityClient)
                .transactionDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .status(TransactionStatus.ACTIVE)
                .build();
    }

    private static Double assignValueAmount(Double amount, TransactionType type) {
        final var val = Math.abs(amount);
        if (TransactionType.Deposit.equals(type)) {
            return val;
        } else {
            return (-1) * val;
        }
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
                .entityClient(trx.getEntityClient())
                .origin(trx.getOrigin())
                .build();
    }

}
