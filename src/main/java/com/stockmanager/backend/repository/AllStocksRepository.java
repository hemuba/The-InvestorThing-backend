package com.stockmanager.backend.repository;

import com.stockmanager.backend.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllStocksRepository extends JpaRepository<Stock, String> {

    List<Stock> findBySectorIgnoreCase(String sector);

    Stock findByTickerIgnoreCase(String ticker);
}
