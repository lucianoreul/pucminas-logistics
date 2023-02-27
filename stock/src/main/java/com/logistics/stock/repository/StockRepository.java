package com.logistics.stock.repository;

import com.logistics.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * CRUD Repository for entity: Stock.
 *
 * @author LucianoReul
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * Find a stock by id
     *
     * @param id
     * @return Optional<Stock>
     */
    Optional<Stock> findById(Integer id);

    /**
     * get stock list
     *
     * @return
     */
    List<Stock> findAll();
}
