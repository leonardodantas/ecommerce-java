package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.Promotion;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PromotionResponseDTO {

    private final BigDecimal oldPrice;
    private final BigDecimal newPrice;
    private final double discountPercentage;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String productId;

    private PromotionResponseDTO(Promotion promotion) {
        this.oldPrice = promotion.getOldPrice();
        this.newPrice = promotion.getPrice();
        this.discountPercentage = promotion.getDiscountPercentage();
        this.start = promotion.getStart();
        this.end = promotion.getEnd();
        this.productId = promotion.getId();
    }

    public static PromotionResponseDTO of(Promotion promotion) {
        return new PromotionResponseDTO(promotion);
    }
}
