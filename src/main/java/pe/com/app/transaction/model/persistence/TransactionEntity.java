package pe.com.app.transaction.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.app.transaction.common.config.Currency;
import pe.com.app.transaction.common.config.TransactionStatus;
import pe.com.app.transaction.common.config.TransactionType;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "transactions")
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = -6734174993200993742L;
    @Id
    private String id;
    private String serviceId;
    private Double amount;
    private Currency currency; // Moneda (USD, PEN, EUR, etc.)

    private TransactionType type;
    private TransactionStatus status;

    private LocalDateTime transactionDate;

    //Audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
