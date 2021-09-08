package com.br.servico.api.produtos.models.entity;

import com.br.servico.api.produtos.models.request.ProductRequestDTO;
import com.br.servico.api.produtos.models.request.TagsRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@Getter
public class Product {

    @Id
    private String id;
    @Column(name = "nome", length = 120)
    private String name;
    @Column(name = "tags")
    @OneToMany
    @JoinColumn(name = "tag_id")
    private List<Tags> tags;
    @OneToMany(mappedBy = "product")
    private List<Promotion> promotions;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<PriceHistory> priceHistories;

    private Product(ProductRequestDTO productRequestDTO) {
        this.id = UUID.randomUUID().toString();
        this.name = productRequestDTO.getName();
        this.tags = productRequestDTO.getTags().stream().map(Tags::of).collect(Collectors.toUnmodifiableList());
        this.priceHistories = Collections.singletonList(PriceHistory.of(id, productRequestDTO.getPrice()));
    }

    private Product(String idProduct) {
        this.id = idProduct;
    }

    private Product(ProductRequestDTO productRequestDTO, List<TagsRequestDTO> tagsRequestDTOS) {
        this.id = UUID.randomUUID().toString();
        this.name = productRequestDTO.getName();
        this.tags = tagsRequestDTOS.stream().map(Tags::of).collect(Collectors.toUnmodifiableList());
        this.priceHistories = Collections.singletonList(PriceHistory.of(id, productRequestDTO.getPrice()));
        this.promotions = new ArrayList<>();
    }

    public static Product of(ProductRequestDTO productRequestDTO) {
        return new Product(productRequestDTO);
    }

    public static Product of(String idProduct) {
        return new Product(idProduct);
    }

    public static Product of(ProductRequestDTO productRequestDTO, List<TagsRequestDTO> tagsRequestDTOS) {
        return new Product(productRequestDTO, tagsRequestDTOS);
    }

    public BigDecimal getPrice() {
        return getPriceHistories().stream()
                .filter(priceHistory -> Objects.isNull(priceHistory.getEnd())).findFirst()
                .get().getPrice();
    }
}
