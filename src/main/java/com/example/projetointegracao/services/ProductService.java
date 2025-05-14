package com.example.projetointegracao.services;

import com.example.projetointegracao.dto.ProductDTO;

import java.util.List;

public class ProductService {
    private MainService mainService;

    public ProductService() {
        mainService = new MainService();
    }

    public List<ProductDTO> getProductsByCategory(Long id) {
        return mainService.getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/products").queryParam("categoryId", "{id}").build(id))
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .collectList()
                .block();
    }
}
