package com.logistics.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * DTO for entity: Stock.
 *
 * @author LucianoReul
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    /**
     * DRAWING
     */
    @NotNull(message = "O código do material é necessário.")
    private Long drawing;

    /**
     * LOCATION
     */
    @NotNull(message = "A localização do material é necessário.")
    private Long location;

    /**
     * QUANTITY
     */
    @NotNull(message = "A quantidade do material é necessário.")
    private Integer quantity;

    /**
     * DESCRIPTION
     */
    private String description;

    /**
     * MDR
     */
    private Long mdr;

    /**
     * PARTIAL
     */
    private Boolean partial;

    /**
     * STATUS
     */
    @NotNull(message = "Status do pedido é necessário")
    private Integer status;

    /**
     * JUSTIFY
     */
    private String justify;
}
