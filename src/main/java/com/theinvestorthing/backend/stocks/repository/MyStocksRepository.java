package com.theinvestorthing.backend.stocks.repository;

import com.theinvestorthing.backend.stocks.model.MyStock;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyStocksRepository extends JpaRepository<MyStock, String> {

    Optional<MyStock> findByTickerIgnoreCase(String ticker);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM MyStock s WHERE upper(s.ticker) = upper(:ticker)")
    void deleteByTickerIgnoreCase(@Param("ticker") String ticker);

    List<MyStock> findByCurrentReturnGreaterThan(Double value, Sort sort);
}
