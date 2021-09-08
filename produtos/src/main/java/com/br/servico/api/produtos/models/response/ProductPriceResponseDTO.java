package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.Product;
import lombok.Getter;

@Getter
public class ProductPriceResponseDTO {

    private final String id;
    private final String name;

    private ProductPriceResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
    }

    public static ProductPriceResponseDTO of(Product product) {
        return new ProductPriceResponseDTO(product);
    }
}
