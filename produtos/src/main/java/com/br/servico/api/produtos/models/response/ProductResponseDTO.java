package com.br.servico.api.produtos.models.response;

import com.br.servico.api.produtos.models.entity.Product;
import com.br.servico.api.produtos.utils.ProductPriceUtils;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductResponseDTO {

    private final String id;
    private final String name;
    private final BigDecimal price;
    private final List<TagsResponseDTO> tags;

    private ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = ProductPriceUtils.getPrice(product);
        this.tags = product.getTags().stream().map(TagsResponseDTO::of).collect(Collectors.toUnmodifiableList());
    }

    public static ProductResponseDTO of(Product product) {
        return new ProductResponseDTO(product);
    }
}
