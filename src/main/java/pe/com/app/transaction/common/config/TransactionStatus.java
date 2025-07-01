package pe.com.app.transaction.common.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionStatus {
    ACTIVE("ACTIVE"),
    OFFERED("OFFERED"),
    INACTIVE("INACTVIE");

    private final String description;

    TransactionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static TransactionStatus fromString(String value) {
        return value != null ? TransactionStatus.valueOf(value.toUpperCase()) : null;
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
