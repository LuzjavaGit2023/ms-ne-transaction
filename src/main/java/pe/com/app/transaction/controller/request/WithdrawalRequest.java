package pe.com.app.transaction.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.transaction.common.config.Currency;

import java.io.Serializable;

/**
 * <b>class</b>: WithdrawalNewRequest <br/>
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
public class WithdrawalRequest implements Serializable {
    private static final long serialVersionUID = 7200689905158346632L;
    private String serviceId;
    private Double amount;
    private Currency currency; // Moneda (USD, PEN, EUR, etc.)

}
