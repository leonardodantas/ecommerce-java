package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.PriceHistory;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PagePriceHistoryResponseDTO extends PageResponse {

    private final List<PriceHistoryResponseDTO> body;

    private PagePriceHistoryResponseDTO(Page<PriceHistory> priceHistoryList) {
        super(priceHistoryList.getPageable().getPageNumber(), priceHistoryList.getPageable().getPageSize(), priceHistoryList.getTotalElements());
        this.body = priceHistoryList.getContent().stream().map(PriceHistoryResponseDTO::of).collect(Collectors.toUnmodifiableList());
    }

    public static PagePriceHistoryResponseDTO of(Page<PriceHistory> priceHistoryList) {
        return new PagePriceHistoryResponseDTO(priceHistoryList);
    }
}
