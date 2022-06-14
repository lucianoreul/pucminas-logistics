package com.ms.logistics.stock.controller;

import com.ms.logistics.stock.dto.StockDTO;
import com.ms.logistics.stock.services.StockService;
import com.ms.logistics.stock.vo.StockVO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;

    @PostMapping
    public ResponseEntity<StockVO> create (@Valid @NotNull @RequestBody StockDTO dto) {
        return ResponseEntity.ok(this.stockService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<StockVO>> getAll(@Valid @NotNull @RequestParam boolean operation) {
        return ResponseEntity.ok(this.stockService.getAll(operation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockVO> getById(@Valid @NotNull @PathVariable Integer id) {
        return ResponseEntity.ok(this.stockService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockVO> update (
            @Valid @NotNull @PathVariable Integer id,
            @Valid @NotNull @RequestBody StockDTO dto
    ) {
        return ResponseEntity.ok(this.stockService.update(id, dto));
    }
}
