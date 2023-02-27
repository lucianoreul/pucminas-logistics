package com.logistics.stock.enums;

import lombok.Getter;

/**
 * Enum to define state of the stock
 *
 * @author LucianoReul
 */
@Getter
public enum StockState {

    RECEIVED(1,"Received"),
    TRANSIT(2,"In Transit"),
    CONCLUDED(3,"Concluded"),
    PROBLEM(4,"Problem");

    private final Integer code;
    private final String description;

    StockState(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Convert a code to enum
     *
     * @param code
     * @return
     */
    public static StockState toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for(StockState status: StockState.values()) {
            if (code.equals(status.getCode())) {
                return status;
            }
        }

        return null;
    }
}
