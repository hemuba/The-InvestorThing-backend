package com.theinvestorthing.backend.old;


import com.theinvestorthing.backend.common.exception.BadRequestException;
import com.theinvestorthing.backend.common.response.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping("/stocks")
public class StocksController_OLD {

    private final StocksService_OLD stocksServiceOLD;

    public StocksController_OLD(StocksService_OLD stocksServiceOLD) {
        this.stocksServiceOLD = stocksServiceOLD;
    }

                // GET METHODS //

    @GetMapping
    public ResponseEntity<ApiResponse> getAllStocks() {
        List<StockDTOResponse_OLD> respObj = stocksServiceOLD.getAllStocks();
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stocks", respObj));
    }

    @GetMapping("/by")
    public ResponseEntity<ApiResponse> getStocksBy(
            @RequestParam(required = false) String ticker,
            @RequestParam(required = false) String sector
    ) {
        if (ticker == null && sector == null) {
            throw new BadRequestException("Missing or wrong query parameters.");
        }

        List<StockDTOResponse_OLD> respObj = stocksServiceOLD.getStockBy(ticker, sector);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stocks", respObj));
    }


                // POST METHODS //

    @PostMapping
    public ResponseEntity<ApiResponse> addStock(@Valid @RequestBody StockDTORequest_OLD stockDTORequestOLD) {
        StockDTOResponse_OLD obj = stocksServiceOLD.addStock(stockDTORequestOLD);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "Stock_OLD " + stockDTORequestOLD.getTicker() + " successfully added to the database", obj));
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse> addStocks(@Valid @RequestBody List<StockDTORequest_OLD> stocksDTORequest) {
        List<StockDTOResponse_OLD> obj = stocksServiceOLD.addStocks(stocksDTORequest);
        return ResponseEntity.status(201).body(new ApiResponse(LocalDateTime.now(), 201, "Stocks successfully added to the Database", obj));
    }


            // DELETE METHOD //

    @DeleteMapping("/{ticker}")
    public ResponseEntity<ApiResponse> removeStock(@PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker) {
        String respBody = stocksServiceOLD.removeStock(ticker);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, respBody, stocksServiceOLD.getAllStocks()));
    }


            // UPDATE METHOD //

    @PutMapping("/{ticker}")
    public ResponseEntity<ApiResponse> updateStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank.") String ticker,
            @Valid @RequestBody StockDTORequest_OLD stockDTORequestOLD
    ) {
        StockDTOResponse_OLD obj = stocksServiceOLD.updateStock(ticker, stockDTORequestOLD);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock_OLD successfully updated", obj));
    }


            // PATCH METHOD //

    @PatchMapping("/{ticker}")
    public ResponseEntity<ApiResponse> patchStock(
            @PathVariable @NotBlank(message = "Ticker cannot be blank") String ticker,
            @RequestBody StockDTOPatch_OLD stockDTOPatchOLD
    ) {
        StockDTOResponse_OLD obj = stocksServiceOLD.patchStock(ticker, stockDTOPatchOLD);
        return ResponseEntity.status(200).body(new ApiResponse(LocalDateTime.now(), 200, "Stock_OLD successfully patched", obj));
    }

}
