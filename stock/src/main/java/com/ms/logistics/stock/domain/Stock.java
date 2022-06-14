package com.ms.logistics.stock.domain;

import com.ms.logistics.stock.dto.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "drawing", nullable = false)
    private Long drawing;

    @Column(name = "location", nullable = false)
    private Long location;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "mdr")
    private Long mdr;

    @Column(name = "partial")
    private Boolean partial;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @Column(name = "justify")
    private String justify;

    public Stock (StockDTO dto) {
        this.drawing = dto.getDrawing();
        this.location = dto.getLocation();
        this.quantity = dto.getQuantity();
        this.description = dto.getDescription();
        this.mdr = dto.getMdr();
        this.partial = dto.getPartial();
        this.status = dto.getStatus();
        this.justify = dto.getJustify();
    }
}
