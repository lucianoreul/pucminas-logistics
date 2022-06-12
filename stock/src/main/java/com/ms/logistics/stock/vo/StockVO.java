package com.ms.logistics.stock.vo;

import com.ms.logistics.stock.domain.Stock;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class StockVO {

    private Integer id;

    private Long enter;

    private Long request_number;

    private Long drawing;

    private Long mdr;

    private Long location;

    private Integer quantity;

    private boolean partial;

    private String description;

    public StockVO(Stock stock) {
        BeanUtils.copyProperties(stock, this);
    }
}
