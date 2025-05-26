package com.theinvestorthing.backend.analytics.controller;


import com.theinvestorthing.backend.analytics.service.AnalyticsService;
import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@Validated
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/wallet")
    public Mono<ApiResponse<List<MyCryptoDTOResp>>> getWallet(){
        return analyticsService.getWallet();

    }
}
