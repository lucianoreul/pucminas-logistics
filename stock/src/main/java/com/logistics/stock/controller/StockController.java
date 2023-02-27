package com.logistics.stock.controller;

import com.logistics.stock.dto.StockDTO;
import com.logistics.stock.vo.StockVO;
import com.logistics.stock.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rest Controller for Stock microsservice
 *
 * @author LucianoReul
 */
@RestController
@AllArgsConstructor
@RequestMapping("/stock")
public class StockController {

    /**
     * Stock service
     */
    private StockService stockService;

    /**
     * create stock endpoint
     *
     * @param dto
     * @return StockVO
     */
    @PostMapping
    public ResponseEntity<StockVO> create (@Valid @NotNull @RequestBody StockDTO dto) {
        return ResponseEntity.ok(this.stockService.create(dto));
    }

    /**
     * Get stock list endpoint
     *
     * @param operation
     * @return List<StockVO>
     */
    @GetMapping
    public ResponseEntity<List<StockVO>> getAll(@Valid @NotNull @RequestParam boolean operation) {
        return ResponseEntity.ok(this.stockService.getAll(operation));
    }

    /**
     * Get stock endpoint
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<StockVO> getById(@Valid @NotNull @PathVariable Integer id) {
        return ResponseEntity.ok(this.stockService.getById(id));
    }

    /**
     * Update stock endpoint
     *
     * @param id
     * @param dto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<StockVO> update (
            @Valid @NotNull @PathVariable Integer id,
            @Valid @NotNull @RequestBody StockDTO dto
    ) {
        return ResponseEntity.ok(this.stockService.update(id, dto));
    }
}
