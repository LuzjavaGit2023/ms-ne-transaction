package pe.com.app.transaction.controller.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.transaction.common.config.Currency;

/**
 * <b>class</b>: DepositRequest <br/>
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
public class DepositRequest implements Serializable {
    private static final long serialVersionUID = 7542980403486085259L;
    private String serviceId;
    private Double amount;
    private Currency currency; // Moneda (USD, PEN, EUR, etc.)

    private String entityClient;
}
