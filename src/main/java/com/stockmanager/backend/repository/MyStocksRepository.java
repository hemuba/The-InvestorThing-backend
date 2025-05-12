package com.stockmanager.backend.repository;

import com.stockmanager.backend.model.MyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MyStocksRepository extends JpaRepository<MyStock, String> {

    public MyStock findByTickerIgnoreCase(String ticker);

}
