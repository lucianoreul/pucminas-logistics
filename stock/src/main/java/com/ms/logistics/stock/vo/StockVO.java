package com.ms.logistics.stock.vo;

import com.ms.logistics.stock.domain.Stock;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class StockVO {

    private Integer id;

    private Long drawing;

    private Long location;

    private Integer quantity;

    private String description;

    private Long mdr;

    private Boolean partial;

    private Integer status;

    private String justify;

    public StockVO(Stock stock) {
        BeanUtils.copyProperties(stock, this);
    }
}
