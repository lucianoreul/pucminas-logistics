package com.ms.logistics.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    @NotNull(message = "O código do material é necessário.")
    private Long drawing;

    @NotNull(message = "A localização do material é necessário.")
    private Long location;

    @NotNull(message = "A quantidade do material é necessário.")
    private Integer quantity;

    private String description;

    private Long mdr;

    private Boolean partial;

    @NotNull(message = "Status do pedido é necessário")
    private Integer status;

    private String justify;
}
