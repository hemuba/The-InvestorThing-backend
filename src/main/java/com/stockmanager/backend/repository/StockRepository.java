package com.stockmanager.backend.repository;

import com.stockmanager.backend.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stocks, String> {

    List<Stocks> findBySectorIgnoreCase(String sector);

    Stocks findByTickerIgnoreCase(String ticker);
}
