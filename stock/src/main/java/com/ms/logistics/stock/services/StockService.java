package com.ms.logistics.stock.services;

import com.ms.logistics.stock.domain.Stock;
import com.ms.logistics.stock.exception.BusinessException;
import com.ms.logistics.stock.repository.StockRepository;
import com.ms.logistics.stock.vo.StockVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Optional<Stock> findById(Integer id) {
        return this.stockRepository.findById(id);
    }

    public StockVO getById(Integer id) throws BusinessException {
        Optional<Stock> stockOptional = this.findById(id);
        if (stockOptional.isPresent()) {
            return new StockVO(stockOptional.get());
        } else {
            throw new BusinessException("User not found");
        }
    }
}
