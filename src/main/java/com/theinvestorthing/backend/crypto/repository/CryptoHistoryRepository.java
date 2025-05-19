package com.theinvestorthing.backend.crypto.repository;


import com.theinvestorthing.backend.crypto.model.CryptoHistory;
import com.theinvestorthing.backend.crypto.model.CryptoHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoHistoryRepository extends JpaRepository<CryptoHistory, CryptoHistoryId> {

    @Query("SELECT h FROM CryptoHistory h WHERE UPPER(h.id.crypto.symbol) = UPPER(:symbol) ORDER BY h.id.data")
    List<CryptoHistory> findHistoryBySymbolIgnoreCase(@Param("symbol") String symbol);


}
