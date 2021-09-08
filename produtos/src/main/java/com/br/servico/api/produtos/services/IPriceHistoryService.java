package com.br.servico.api.produtos.services;

import com.br.servico.api.produtos.models.response.PagePriceHistoryResponseDTO;
import com.br.servico.api.produtos.models.response.PriceProductResponseDTO;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

public interface IPriceHistoryService {

    PriceProductResponseDTO updatePriceProduct(String idProduct, BigDecimal price);
    PagePriceHistoryResponseDTO getAllProductPrice(String id, PageRequest pageable);
    PriceProductResponseDTO getLastPrice(String productId);
}
