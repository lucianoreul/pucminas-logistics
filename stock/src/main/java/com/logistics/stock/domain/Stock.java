package com.logistics.stock.domain;

import com.logistics.stock.dto.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Model for table: Stock.
 *
 * @author LucianoReul
 */
@Entity(name = "stock")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stock {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * DRAWING
     */
    @Column(name = "drawing", nullable = false)
    private Long drawing;

    /**
     * LOCATION
     */
    @Column(name = "location", nullable = false)
    private Long location;

    /**
     * QUANTITY
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * DESCRIPTION
     */
    @Column(name = "description")
    private String description;

    /**
     * MDR
     */
    @Column(name = "mdr")
    private Long mdr;

    /**
     * PARTIAL
     */
    @Column(name = "partial")
    private Boolean partial;

    /**
     * STATUS
     */
    @Column(name = "status", nullable = false)
    private Integer status = 1;

    /**
     * JUSTIFY
     */
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
