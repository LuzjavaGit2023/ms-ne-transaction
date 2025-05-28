package pe.com.app.transaction.advice;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 2747967363548965555L;
    private String error;
    private String message;
    private String timestamp;
}
