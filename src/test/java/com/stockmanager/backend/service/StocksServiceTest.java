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

    StockDTORequest req = new StockDTORequest(
            "nvda", "Nvidia", "Technology", 10.00, 50.00, 115.00, null
    );

    StockDTOResponse resp = stocksService.addStock(req);

    assertEquals("NVDA", resp.getTicker());
  }

  @Test
  void removeStock_ShouldThrowException(){
    CurrentStocks currentStocks = new CurrentStocks();
    StocksService stocksService = new StocksService(currentStocks);

    StockDTORequest req = new StockDTORequest(
            "nvda", "Nvidia", "Technology", 10.00, 50.00, 115.00, null
    );

    StockDTOResponse resp = stocksService.addStock(req);


    assertThrows(NotFoundException.class, () ->  stocksService.removeStock("TEST"));

  }


}
