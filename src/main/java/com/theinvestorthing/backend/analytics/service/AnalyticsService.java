package com.theinvestorthing.backend.analytics.service;

import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AnalyticsService {

    private final WebClient webClient;

    public AnalyticsService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8080/the-investorthing")
                .build();
    }

    public Mono<ApiResponse<CryptoDTOResp>> getCryptoBySymbol(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/all-crypto/by-symbol")
                        .queryParam("symbol", symbol)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<CryptoDTOResp>>() {})
                .doOnError(err -> err.printStackTrace());

    }

}



