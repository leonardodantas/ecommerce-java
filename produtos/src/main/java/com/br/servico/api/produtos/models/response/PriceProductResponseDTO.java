package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.PriceHistory;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PriceProductResponseDTO {

    private final String id;
    private final ProductPriceResponseDTO product;
    private final BigDecimal price;
    private final LocalDateTime start;

    private PriceProductResponseDTO(PriceHistory priceHistory) {
        this.id = priceHistory.getId();
        this.product = ProductPriceResponseDTO.of(priceHistory.getProduct());
        this.price = priceHistory.getPrice();
        this.start = priceHistory.getStart();
    }

    public static PriceProductResponseDTO of(PriceHistory priceHistory) {
        return new PriceProductResponseDTO(priceHistory);
    }
}
