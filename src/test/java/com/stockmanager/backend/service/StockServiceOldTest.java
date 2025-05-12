package com.stockmanager.backend.service;

import com.stockmanager.backend.dto.StockDTOPatchOld;
import com.stockmanager.backend.dto.StockDTORequestOld;
import com.stockmanager.backend.dto.StockDTOResponseOld;
import com.stockmanager.backend.exception.BadRequestException;
import com.stockmanager.backend.exception.NotFoundException;
import com.stockmanager.backend.old.CurrentStocksOld;
import com.stockmanager.backend.old.StocksServiceOld;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

class StockServiceOldTest {
  @Test
    void addStock_CurrentTotal_Ticker_ShouldReturnOK(){

      CurrentStocksOld currentStocks = new CurrentStocksOld();
      StocksServiceOld stocksServiceOld = new StocksServiceOld(currentStocks);

      StockDTORequestOld req = new StockDTORequestOld(
              "nvda", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
      );

      StockDTOResponseOld resp = stocksServiceOld.addStock(req);

      assertEquals("NVDA", resp.getTicker()); // ticker should be in UpperCase
      assertEquals(BigDecimal.valueOf(req.getNoOfShares() * req.getCurrentPrice()).setScale(2, RoundingMode.HALF_UP), resp.getCurrentTotal()); // exact amount

  }

  @Test
  void addStock_TickerAlreadyExists_ShouldThrowException(){
    CurrentStocksOld currentStocks = new CurrentStocksOld();
    StocksServiceOld stocksServiceOld = new StocksServiceOld(currentStocks);

    StockDTORequestOld req = new StockDTORequestOld(
            "NVDA", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
    );

    stocksServiceOld.addStock(req); // 1st add in order to trigger the exception "the ticker already exists"

    assertThrows(BadRequestException.class, () -> stocksServiceOld.addStock(req)); // readded the same

  }

  @Test
  void deleteStock_ShouldThrowException(){
    CurrentStocksOld currentStocks = new CurrentStocksOld();
    StocksServiceOld stocksServiceOld = new StocksServiceOld(currentStocks);

    StockDTORequestOld req = new StockDTORequestOld(

            "NVDA", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
    );

    StockDTOResponseOld resp = stocksServiceOld.addStock(req);

    assertThrows(NotFoundException.class, () -> stocksServiceOld.removeStock("TEST")); // check if it fails with a ticker which does not exists
  }

  @Test
  void patchStock_ShouldReturnOK(){
    CurrentStocksOld currentStocks = new CurrentStocksOld();
    StocksServiceOld stocksServiceOld = new StocksServiceOld(currentStocks);
    StockDTORequestOld req = new StockDTORequestOld("NVDA", "Nvidia", "Technology", 10.0, 150.0, 170.0, null);
    stocksServiceOld.addStock(req);

    StockDTOPatchOld patch = new StockDTOPatchOld();
    patch.setTicker("NVDAPATCHED"); // in order to test the patching I've used the empty constructor
    patch.setCurrentPrice(1.0); // in order to test the patching I've used the empty constructor
    StockDTOResponseOld patched = stocksServiceOld.patchStock("NVDA", patch);

    assertEquals("NVDAPATCHED", patched.getTicker()); // ticker has been patched
    assertEquals(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP), patched.getCurrentTotal()); // 10 * 1 = 10
  }

  @Test
  void updateStock(){
    CurrentStocksOld currentStocks = new CurrentStocksOld();
    StocksServiceOld stocksServiceOld = new StocksServiceOld(currentStocks);

    StockDTORequestOld req = new StockDTORequestOld(
            "nvda", "Nvidia", "Technology", 3.0, 150.0, 170.0, null
    );

    stocksServiceOld.addStock(req);
    StockDTORequestOld update = new StockDTORequestOld(
            "nvdaupdated", "Nvidia", "Technology", 1.0, 150.0, 170.0, null
    );

    StockDTOResponseOld updated = stocksServiceOld.updateStock(req.getTicker(), update);

    assertEquals("NVDAUPDATED", updated.getTicker());
    assertEquals(BigDecimal.valueOf(170).setScale(2, RoundingMode.HALF_UP), updated.getCurrentTotal());
  }
}
