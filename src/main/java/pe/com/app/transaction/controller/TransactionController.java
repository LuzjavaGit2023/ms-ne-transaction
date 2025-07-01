package pe.com.app.transaction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.app.transaction.advice.ErrorResponse;
import pe.com.app.transaction.controller.request.*;
import pe.com.app.transaction.controller.response.TransactionDataResponse;
import pe.com.app.transaction.controller.response.TransactionResponse;
import pe.com.app.transaction.service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <b>Class</b>: TransactionController <br/>
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Functional operations related to transactions")
public class TransactionController {

    private final TransactionService service;

    /**
     * This method is used to create a Commission to an Account element.
     *
     * @return CommissionResponse Mono.
     */
    @PostMapping("/{serviceId}/commission")
    @Operation(summary = "This method is used to create a Commission to an Account element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<TransactionResponse> saveCommission(@PathVariable String serviceId,
                                                    @RequestBody CommissionRequest request) {
        return service.saveCommission(serviceId, request);
    }

    /**
     * This method is used to create a Withdrawal to an Account element.
     *
     * @return WithdrawalResponse Mono.
     */
    @PostMapping("/{serviceId}/withdrawal")
    @Operation(summary = "This method is used to create a Withdrawal to an Account element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<TransactionResponse> saveWithdrawal(@PathVariable String serviceId,
                                                    @RequestBody WithdrawalRequest request) {
        return service.saveWithdrawal(serviceId, request);
    }

    /**
     * This method is used to save a consumption to a Credit element.
     *
     * @return ConsumptionResponse Mono.
     */
    @PostMapping("/{serviceId}/consumption")
    @Operation(summary = "This method is used to save a consumption to an Credit element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<TransactionResponse> saveConsumption(@PathVariable String serviceId,
                                                     @RequestBody ConsumptionRequest request) {
        return service.saveConsumption(serviceId, request);
    }

    /**
     * This method is used to save a payment to a Credit element.
     *
     * @return PaymentResponse Mono.
     */
    @PostMapping("/{serviceId}/payment")
    @Operation(summary = "This method is used to save a payment to Credit element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<TransactionResponse> savePayment(@PathVariable String serviceId, @RequestBody PaymentRequest request) {
        return service.savePayment(serviceId, request);
    }

    /**
     * This method is used to save a Deposit to an Account element.
     *
     * @return DepositResponse Mono.
     */
    @PostMapping("/{serviceId}/deposit")
    @Operation(summary = "This method is used to save a Deposit to an Account element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<TransactionResponse> saveDeposit(@PathVariable String serviceId, @RequestBody DepositRequest request) {
        return service.saveDeposit(serviceId, request);
    }

    /**
     * This method is used to save a Trx Electronic Money to an Account element.
     *
     * @return DepositResponse Mono.
     */
    @PostMapping("/{serviceId}/electronic-money")
    @Operation(summary = "This method is used to save a Trx Electronic Money to an Account element.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Mono<TransactionResponse> saveStartedTrxElectronicMoney(@PathVariable String serviceId,
                                                                   @RequestBody TransactionElectronicMoneyRequest request) {
        return service.saveTransactionElectronicMoney(serviceId, request);
    }

    /**
     * This method is used to get all transactions about a service.
     *
     * @return AccountResponse Mono.
     */
    @GetMapping("/{serviceId}")
    @Operation(summary = "This method is used to get all transactions about a service.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Flux<TransactionDataResponse> getTransactionsByServiceId(@PathVariable String serviceId) {
        return service.getTransactionsByServiceId(serviceId);
    }

    /**
     * This method is used to get all transactions.
     *
     * @return AccountResponse Mono.
     */
    @GetMapping
    @Operation(summary = "This method is used to get all transactions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Flux<TransactionDataResponse> getTransactionsAll() {
        return service.getTransactionsAll();
    }

    /**
     * This method is used to get all Commission transactions.
     *
     * @return AccountResponse Mono.
     */
    @GetMapping("/commission")
    @Operation(summary = "This method is used to get all Commission transactions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public Flux<TransactionDataResponse> getAllCommission() {
        return service.getAllCommission();
    }
}
