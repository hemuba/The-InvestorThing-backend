package com.theinvestorthing.backend.crypto.repository;

import com.theinvestorthing.backend.crypto.model.MyCrypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyCryptoRepository extends JpaRepository<MyCrypto, String> {

    Optional<MyCrypto> findBySymbolIgnoreCase(String symbol);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM MyCrypto c WHERE upper(c.symbol) = upper(:symbol) ")
    void deleteBySymbolIgnoreCase(String symbol);
}

