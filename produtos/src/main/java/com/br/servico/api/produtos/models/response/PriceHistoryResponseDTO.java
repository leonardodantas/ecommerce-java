package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.PriceHistory;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PriceHistoryResponseDTO {

    private final String id;
    private final BigDecimal price;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public PriceHistoryResponseDTO(PriceHistory priceHistory) {
        this.id = priceHistory.getId();
        this.price = priceHistory.getPrice();
        this.start = priceHistory.getStart();
        this.end = priceHistory.getEnd();
    }

    public static PriceHistoryResponseDTO of(PriceHistory priceHistory) {
        return new PriceHistoryResponseDTO(priceHistory);
    }
}
