package pe.com.app.transaction.controller.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.app.transaction.common.config.Currency;

/**
 * <b>class</b>: TransactionElectronicMoneyRequest <br/>
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
public class TransactionElectronicMoneyRequest implements Serializable {
    private static final long serialVersionUID = -3060157780083809472L;
    private String serviceId;
    private Double amount;
}
