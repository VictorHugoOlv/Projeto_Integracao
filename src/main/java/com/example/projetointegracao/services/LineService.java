package com.example.projetointegracao.services;

import com.example.projetointegracao.dto.LineDTO;

import java.util.List;

public class LineService {
    private MainService mainService;

    public LineService() {
        mainService = new MainService();
    }

    public List<LineDTO> getAllLines() {
        return mainService.getWebClient()
                .get()
                .uri("/lines")
                .retrieve()
                .bodyToFlux(LineDTO.class)
                .collectList()
                .block();
    }
}
