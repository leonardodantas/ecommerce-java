package com.br.servico.api.produtos.services;

import com.br.servico.api.produtos.models.request.PromotionPriceRequestDTO;
import com.br.servico.api.produtos.models.response.PagePromotionResponseDTO;
import com.br.servico.api.produtos.models.response.PromotionResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IPromotionService {
    PromotionResponseDTO createPromotion(PromotionPriceRequestDTO promotionPriceRequestDTO);
    PagePromotionResponseDTO getAllPromotion(String idProduct, Pageable pageable);
}
