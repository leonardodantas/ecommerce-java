package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.Product;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PageProductsResponseDTO extends PageResponse {

    private final List<ProductResponseDTO> body;

    private PageProductsResponseDTO(Page<Product> productPage) {
        super(productPage.getPageable().getPageNumber(),productPage.getPageable().getPageSize(), productPage.getTotalElements());
        this.body = productPage.getContent().stream().map(ProductResponseDTO::of).collect(Collectors.toUnmodifiableList());
    }

    public static PageProductsResponseDTO of(Page<Product> productPage) {
        return new PageProductsResponseDTO(productPage);
    }
}
