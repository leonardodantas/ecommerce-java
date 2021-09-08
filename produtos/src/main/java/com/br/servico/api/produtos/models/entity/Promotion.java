package com.br.servico.api.produtos.models.entity;

import com.br.servico.api.produtos.models.request.PromotionPriceRequestDTO;
import com.br.servico.api.produtos.models.response.PriceProductResponseDTO;
import com.br.servico.api.produtos.models.response.ProductResponseDTO;
import com.br.servico.api.produtos.utils.ProductPriceUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "promocoes")
@NoArgsConstructor
@Getter
public class Promotion {

    @Id
    private String id;
    @Column(name = "preco")
    private BigDecimal price;
    @Column(name = "porcentagem_desconto")
    private double discountPercentage;
    @Column(name = "inicio")
    private LocalDateTime start;
    @Column(name = "fim")
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product product;
    @OneToOne
    @JoinColumn(name = "price_id")
    private PriceHistory priceHistory;

    private Promotion(PromotionPriceRequestDTO promotionPriceRequestDTO, ProductResponseDTO productResponseDTO) {
        this.id = UUID.randomUUID().toString();
        this.price = promotionPriceRequestDTO.getNewPrice();
        this.discountPercentage = ProductPriceUtils.getdiscountPercentage(productResponseDTO.getPrice(), promotionPriceRequestDTO.getNewPrice());
        this.start = promotionPriceRequestDTO.getStart();
        this.end = promotionPriceRequestDTO.getEnd();
        this.product = Product.of(promotionPriceRequestDTO.getProductId());
    }

    private Promotion(PromotionPriceRequestDTO promotionPriceRequestDTO, PriceProductResponseDTO priceProductResponseDTO) {
        this.id = UUID.randomUUID().toString();
        this.price = promotionPriceRequestDTO.getNewPrice();
        this.discountPercentage = ProductPriceUtils.getdiscountPercentage(priceProductResponseDTO.getPrice(), promotionPriceRequestDTO.getNewPrice());
        this.start = promotionPriceRequestDTO.getStart();
        this.end = promotionPriceRequestDTO.getEnd();
        this.product = Product.of(promotionPriceRequestDTO.getProductId());
        this.priceHistory = PriceHistory.of(priceProductResponseDTO);
    }

    public static Promotion of(PromotionPriceRequestDTO promotionPriceRequestDTO, ProductResponseDTO productResponseDTO) {
        return new Promotion(promotionPriceRequestDTO, productResponseDTO);
    }

    public static Promotion of(PromotionPriceRequestDTO promotionPriceRequestDTO, PriceProductResponseDTO priceProductResponseDTO) {
        return new Promotion(promotionPriceRequestDTO, priceProductResponseDTO);
    }

    public BigDecimal getOldPrice(){
        return this.getPriceHistory().getPrice();
    }
}
