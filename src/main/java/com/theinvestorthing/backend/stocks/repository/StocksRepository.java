package com.theinvestorthing.backend.stocks.repository;

import com.theinvestorthing.backend.stocks.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StocksRepository extends JpaRepository<Stock, String> {

    List<Stock> findBySectorIgnoreCase(String sector);

    Optional<Stock> findByTickerIgnoreCase(String ticker);

}
