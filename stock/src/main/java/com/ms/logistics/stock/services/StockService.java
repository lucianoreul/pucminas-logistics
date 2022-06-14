package com.ms.logistics.stock.services;

import com.ms.logistics.stock.domain.Stock;
import com.ms.logistics.stock.dto.StockDTO;
import com.ms.logistics.stock.enums.StockState;
import com.ms.logistics.stock.repository.StockRepository;
import com.ms.logistics.stock.vo.StockVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Optional<Stock> findById(Integer id) {
        return this.stockRepository.findById(id);
    }

    public StockVO getById(Integer id) {
        Optional<Stock> stockOptional = this.findById(id);
        if (stockOptional.isPresent()) {
            return new StockVO(stockOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
    }

    public StockVO create(StockDTO dto) {
        Stock stock = new Stock(dto);
        stockRepository.save(stock);
        return new StockVO(stock);
    }

    public List<StockVO> getAll() {
        return this.stockRepository.findAll().stream().map(StockVO::new).collect(Collectors.toList());
    }

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
