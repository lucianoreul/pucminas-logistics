package com.ms.logistics.stock.domain;

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

    @Column(name = "enter")
    private Long enter;

    @Column(name = "request_number")
    private Long request_number;

    @Column(name = "drawing")
    private Long drawing;

    @Column(name = "mdr")
    private Long mdr;

    @Column(name = "location", nullable = false)
    private Long location;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "partial")
    private boolean partial;

    @Column(name = "description")
    private String description;
}
