package com.example.projetointegracao.services;

import com.example.projetointegracao.dto.CategoryDTO;

import java.util.List;

public class CategoryService {
    private MainService mainService;

    public CategoryService() {
        mainService = new MainService();
    }

    public List<CategoryDTO> getCategoriesByLine(Long id) {
        return mainService.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/categories").queryParam("lineId","{id}").build(id))
                .retrieve()
                .bodyToFlux(CategoryDTO.class)
                .collectList()
                .block();
    }
}

