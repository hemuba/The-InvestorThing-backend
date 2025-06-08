package com.theinvestorthing.backend.stock_transactions.repository;


import com.theinvestorthing.backend.stock_transactions.model.StockTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<StockTransactions, Long> {

    List<StockTransactions> findByTickerIgnoreCase(String ticker);

}
