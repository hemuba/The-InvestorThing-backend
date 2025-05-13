package com.stockmanager.backend.repository;


import com.stockmanager.backend.model.Etf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtfRepository extends JpaRepository<Etf, String> {

    List<Etf> findByThemeIgnoreCase(String theme);

    Optional<Etf> findByTickerIgnoreCase(String ticker);
}
