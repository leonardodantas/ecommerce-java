package com.br.servico.api.produtos.models.entity;

import com.br.servico.api.produtos.models.response.PriceProductResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "historico_precos")
@NoArgsConstructor
@Getter
public class PriceHistory {

    @Id
    private String id;
    @Column(name = "preco")
    private BigDecimal price;
    @Column(name = "inicio")
    private LocalDateTime start;
    @Column(name = "fim")
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product product;

    private PriceHistory(String idProduct, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.start = LocalDateTime.now();
        this.product = Product.of(idProduct);
    }

    private PriceHistory(PriceProductResponseDTO priceProductResponseDTO) {
        this.id = priceProductResponseDTO.getId();
    }

    public static PriceHistory of(String idProduct, BigDecimal price) {
        return new PriceHistory(idProduct, price);
    }

    public static PriceHistory of(PriceProductResponseDTO priceProductResponseDTO) {
        return new PriceHistory(priceProductResponseDTO);
    }

    public void updatePriceEnd() {
        this.end = LocalDateTime.now();
    }
}
