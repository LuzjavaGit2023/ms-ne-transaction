package pe.com.app.transaction.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.app.transaction.common.config.TransactionType;
import pe.com.app.transaction.model.persistence.TransactionEntity;
import reactor.core.publisher.Flux;

/**
 * <b>Interface</b>: TransactionRepository <br/>
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
public interface TransactionRepository extends ReactiveMongoRepository<TransactionEntity, String> {

    Flux<TransactionEntity> findByServiceId(String clientId);
    Flux<TransactionEntity> findByType(TransactionType type);
}
