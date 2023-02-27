package com.logistics.stock.services;

import com.logistics.stock.domain.Stock;
import com.logistics.stock.dto.StockDTO;
import com.logistics.stock.repository.StockRepository;
import com.logistics.stock.vo.StockVO;
import com.logistics.stock.enums.StockState;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Crud Service for model: Stock.
 *
 * @author LucianoReul
 */
@Service
@AllArgsConstructor
public class StockService {

    /**
     * Stock repository
     */
    private final StockRepository stockRepository;

    /**
     * Find a stock by id
     * @param id
     * @return Optional<Stock>
     */
    public Optional<Stock> findById(Integer id) {
        return this.stockRepository.findById(id);
    }

    /**
     *  Get a stock by id to VO
     *
     * @param id
     * @return StockVO
     */
    public StockVO getById(Integer id) {
        Optional<Stock> stockOptional = this.findById(id);
        if (stockOptional.isPresent()) {
            return new StockVO(stockOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
    }

    /**
     * Create a new Stock
     *
     * @param dto
     * @return StockVO
     */
    public StockVO create(StockDTO dto) {
        Stock stock = new Stock(dto);
        stockRepository.save(stock);
        return new StockVO(stock);
    }

    /**
     * Get stock list
     *
     * @param operation
     * @return List<StockVO>
     */
    public List<StockVO> getAll(boolean operation) {
        if (operation) {
            return this.stockRepository.findAll().stream().filter(stock -> stock.getStatus() == 1 || stock.getStatus() == 2).map(StockVO::new).collect(Collectors.toList());
        } else {
            return this.stockRepository.findAll().stream().filter(stock -> stock.getStatus() == 3 || stock.getStatus() == 4).map(StockVO::new).collect(Collectors.toList());
        }
    }

    /**
     * Update a stock from database
     *
     * @param id
     * @param dto
     * @return StockVO
     */
    @Transactional
    public StockVO update(Integer id, StockDTO dto) {
        Optional<Stock> stockOptional = this.findById(id);
        if (stockOptional.isPresent()) {
            Stock stockToUpdate = stockOptional.get();
            stockToUpdate.setDrawing(dto.getDrawing());
            stockToUpdate.setLocation(dto.getLocation());
            stockToUpdate.setQuantity(dto.getQuantity());
            stockToUpdate.setDescription(dto.getDescription());
            stockToUpdate.setMdr(dto.getMdr());
            stockToUpdate.setPartial(dto.getPartial());
            stockToUpdate.setStatus(StockState.toEnum(dto.getStatus()).getCode());
            stockToUpdate.setJustify(dto.getJustify());
            this.stockRepository.save(stockToUpdate);
            return new StockVO(stockToUpdate);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
    }

    /**
     * change stock status to TRANSIT
     *
     * @param id
     * @return
     */
    public StockVO activate(Integer id) {
        Optional<Stock> stockOptional = this.findById(id);
        if (stockOptional.isPresent()) {
            Stock stockToUpdate = stockOptional.get();
            stockToUpdate.setStatus(StockState.TRANSIT.getCode());
            this.stockRepository.save(stockToUpdate);
            return new StockVO(stockToUpdate);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
    }
}
