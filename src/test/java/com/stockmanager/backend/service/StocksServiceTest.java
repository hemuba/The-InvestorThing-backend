package com.stockmanager.backend.service;

import com.stockmanager.backend.dto.StockDTOPatch;
import com.stockmanager.backend.dto.StockDTORequest;
import com.stockmanager.backend.dto.StockDTOResponse;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.repository.CurrentStocks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

class StocksServiceTest {

  @Test
  void addStock_ShouldReturnOK(){
    CurrentStocks currentStocks = new CurrentStocks();
    StocksService stocksService = new StocksService(currentStocks);

    StockDTORequest stock = new StockDTORequest(
            "nvda", "Nvidia", "Technology", 10.00, 50.00, 115.00, null
    );

    stocksService.addStock(stock);

    assertEquals("NVDA", stock.getTicker());
  }



}
