package com.theinvestorthing.backend.analytics.service;

import com.theinvestorthing.backend.analytics.config.ServerConfig;
import com.theinvestorthing.backend.common.response.ApiResponse;
import com.theinvestorthing.backend.crypto.dto.CryptoDTOResp;
import com.theinvestorthing.backend.crypto.dto.MyCryptoDTOResp;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AnalyticsService {


    private final WebClient webClient;

    public AnalyticsService(WebClient.Builder builder, ServerConfig config) {
        String baseUrl = String.format("%s://%s:%d/%s",
                config.getProtocol(),
                config.getEnv(),
                config.getPort(),
                config.getMainRoute());

        this.webClient = builder
                .baseUrl(baseUrl).build();
    }

    public Mono<ApiResponse<List<MyCryptoDTOResp>>> getWallet() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/my-crypto")
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<MyCryptoDTOResp>>>() {});


    }

}



