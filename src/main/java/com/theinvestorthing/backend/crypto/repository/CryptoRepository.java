package com.theinvestorthing.backend.crypto.repository;

import com.theinvestorthing.backend.crypto.model.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, String> {

     Crypto findByIdIgnoreCase(String id);
}
