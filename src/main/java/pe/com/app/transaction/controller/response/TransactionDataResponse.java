package pe.com.app.transaction.controller.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.transaction.common.config.Currency;
import pe.com.app.transaction.common.config.TransactionStatus;
import pe.com.app.transaction.common.config.TransactionType;

/**
 * <b>class</b>: TransactionDataResponse <br/>
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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionDataResponse implements Serializable {
    private static final long serialVersionUID = -7793454769985670924L;
    private String id;
    private String serviceId;
    private Double amount;
    private Currency currency; // Moneda (USD, PEN, EUR, etc.)

    private TransactionType type;
    private TransactionStatus status;

    private LocalDateTime transactionDate;
}
