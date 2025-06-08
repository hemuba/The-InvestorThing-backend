package com.theinvestorthing.backend.stock_transactions.controller;



import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.stock_transactions.dto.TransactionDTOResp;
import com.theinvestorthing.backend.stock_transactions.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/the-investorthing/stock-transactions")
@Validated
public class TransactionsController {

    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionDTOResp>>> getAllTransaction(){
        List<TransactionDTOResp> obj = transactionService.getAllTransactions();
        return ResponseEntity.ok().body(new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Success",
                obj
        ));
    }

    @GetMapping("/by-ticker")
    public ResponseEntity<ApiResponse<List<TransactionDTOResp>>> getTransactionsByTicker(String ticker){
        List<TransactionDTOResp> obj = transactionService.getTransactionsByTicker(ticker);
        return ResponseEntity.ok().body(new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Success",
                obj
        ));
    }
}
