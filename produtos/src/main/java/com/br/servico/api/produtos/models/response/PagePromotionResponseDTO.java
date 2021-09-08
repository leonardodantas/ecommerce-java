package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.PriceHistory;
import com.br.servico.api.produtos.models.entity.Promotion;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PagePromotionResponseDTO extends PageResponse {

    private final List<PromotionResponseDTO> body;

    private PagePromotionResponseDTO(Page<Promotion> promotion) {
        super(promotion.getPageable().getPageNumber(), promotion.getPageable().getPageSize(), promotion.getTotalElements());
        this.body = promotion.getContent().stream().map(PromotionResponseDTO::of).collect(Collectors.toUnmodifiableList());
    }

    public static PagePromotionResponseDTO of(Page<Promotion> promotion) {
        return new PagePromotionResponseDTO(promotion);
    }
}
