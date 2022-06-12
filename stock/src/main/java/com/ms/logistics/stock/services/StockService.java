package com.ms.logistics.stock.services;

import com.ms.logistics.stock.domain.Stock;
import com.ms.logistics.stock.repository.StockRepository;
import com.ms.logistics.stock.vo.StockVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
