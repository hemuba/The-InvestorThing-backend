package com.stockmanager.backend.repository;

import com.stockmanager.backend.model.MyEtf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MyEtfRepository extends JpaRepository<MyEtf, String> {

    Optional<MyEtf> findByTickerIgnoreCase(String ticker);

    List<MyEtf> buyDateGreaterThan(LocalDate buyDate);

}
