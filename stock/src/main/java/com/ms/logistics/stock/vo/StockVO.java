package com.ms.logistics.stock.vo;

import com.ms.logistics.stock.domain.Stock;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * VO for entity: Stock.
 *
 * @author LucianoReul
 */
@Data
public class StockVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * DRAWING
     */
    private Long drawing;

    /**
     * LOCATION
     */
    private Long location;

    /**
     * QUANTITY
     */
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
    private Integer status;

    /**
     * JUSTIFY
     */
    private String justify;

    public StockVO(Stock stock) {
        BeanUtils.copyProperties(stock, this);
    }
}
