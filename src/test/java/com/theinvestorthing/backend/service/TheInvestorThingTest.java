//package com.theinvestorthing.backend.service;
//
//import com.stockmanager.backend.dto.StockDTOPatch;
//import com.stockmanager.backend.dto.StockDTORequest;
//import com.stockmanager.backend.dto.StockDTOResponse;
//import com.stockmanager.backend.exception.BadRequestException;
//import com.stockmanager.backend.exception.NotFoundException;
//import com.stockmanager.backend.repository.CurrentStocks;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//class TheInvestorThingTest {
//  @Test
//    void addStock_CurrentTotal_Ticker_ShouldReturnOK(){
//
//      CurrentStocks currentStocks = new CurrentStocks();
//      StocksService stocksService = new StocksService(currentStocks);
//
//      StockDTORequest req = new StockDTORequest(
//              "nvda", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
//      );
//
//      StockDTOResponse resp = stocksService.addStock(req);
//
//      assertEquals("NVDA", resp.getTicker()); // ticker should be in UpperCase
//      assertEquals(BigDecimal.valueOf(req.getNoOfShares() * req.getCurrentPrice()).setScale(2, RoundingMode.HALF_UP), resp.getCurrentTotal()); // exact amount
//
//  }
//
//  @Test
//  void addStock_TickerAlreadyExists_ShouldThrowException(){
//    CurrentStocks currentStocks = new CurrentStocks();
//    StocksService stocksService = new StocksService(currentStocks);
//
//    StockDTORequest req = new StockDTORequest(
//            "NVDA", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
//    );
//
//    stocksService.addStock(req); // 1st add in order to trigger the exception "the ticker already exists"
//
//    assertThrows(BadRequestException.class, () -> stocksService.addStock(req)); // readded the same
//
//  }
//
//  @Test
//  void deleteStock_ShouldThrowException(){
//    CurrentStocks currentStocks = new CurrentStocks();
//    StocksService stocksService = new StocksService(currentStocks);
//
//    StockDTORequest req = new StockDTORequest(
//
//            "NVDA", "Nvidia", "Technology", 10.0, 150.0, 170.0, null
//    );
//
//    StockDTOResponse resp = stocksService.addStock(req);
//
//    assertThrows(NotFoundException.class, () -> stocksService.removeStock("TEST")); // check if it fails with a ticker which does not exists
//  }
//
//  @Test
//  void patchStock_ShouldReturnOK(){
//    CurrentStocks currentStocks = new CurrentStocks();
//    StocksService stocksService = new StocksService(currentStocks);
//    StockDTORequest req = new StockDTORequest("NVDA", "Nvidia", "Technology", 10.0, 150.0, 170.0, null);
//    stocksService.addStock(req);
//
//    StockDTOPatch patch = new StockDTOPatch();
//    patch.setTicker("NVDAPATCHED"); // in order to test the patching I've used the empty constructor
//    patch.setCurrentPrice(1.0); // in order to test the patching I've used the empty constructor
//    StockDTOResponse patched = stocksService.patchStock("NVDA", patch);
//
//    assertEquals("NVDAPATCHED", patched.getTicker()); // ticker has been patched
//    assertEquals(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP), patched.getCurrentTotal()); // 10 * 1 = 10
//  }
//
//  @Test
//  void updateStock(){
//    CurrentStocks currentStocks = new CurrentStocks();
//    StocksService stocksService = new StocksService(currentStocks);
//
//    StockDTORequest req = new StockDTORequest(
//            "nvda", "Nvidia", "Technology", 3.0, 150.0, 170.0, null
//    );
//
//    stocksService.addStock(req);
//    StockDTORequest update = new StockDTORequest(
//            "nvdaupdated", "Nvidia", "Technology", 1.0, 150.0, 170.0, null
//    );
//
//    StockDTOResponse updated = stocksService.updateStock(req.getTicker(), update);
//
//    assertEquals("NVDAUPDATED", updated.getTicker());
//    assertEquals(BigDecimal.valueOf(170).setScale(2, RoundingMode.HALF_UP), updated.getCurrentTotal());
//  }
//}
