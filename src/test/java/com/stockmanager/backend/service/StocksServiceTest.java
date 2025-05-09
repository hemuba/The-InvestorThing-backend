package com.stockmanager.backend.service;

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
    void addStock_CurrentTotal_Ticker_ShouldReturnOK(){

      CurrentStocks currentStocks = new CurrentStocks();
      StocksService stocksService = new StocksService(currentStocks);

      StockDTORequest req = new StockDTORequest(
              "nvda", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
      );

      StockDTOResponse resp = stocksService.addStock(req);

      assertEquals("NVDA", resp.getTicker()); // ticker should be in UpperCase
      assertEquals(BigDecimal.valueOf(req.getNoOfShares() * req.getCurrentPrice()).setScale(2, RoundingMode.HALF_UP), resp.getCurrentTotal()); // exact amount
      assertEquals(1, currentStocks.getAllStocks().size()); //

  }

  @Test
  void addStock_TickerAlreadyExists_ShouldThrowException(){
    CurrentStocks currentStocks = new CurrentStocks();
    StocksService stocksService = new StocksService(currentStocks);

    StockDTORequest req = new StockDTORequest(
            "NVDA", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
    );

    stocksService.addStock(req);

    assertThrows(BadRequestException.class, () -> stocksService.addStock(req));

  }

  @Test
  void deleteStock_ShouldThrowException(){
    CurrentStocks currentStocks = new CurrentStocks();
    StocksService stocksService = new StocksService(currentStocks);

    assertThrows(NotFoundException.class, () -> stocksService.removeStock("TEST"));
  }
}
