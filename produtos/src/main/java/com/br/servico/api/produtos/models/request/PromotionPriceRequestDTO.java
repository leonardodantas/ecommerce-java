package com.br.servico.api.produtos.models.request;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PromotionPriceRequestDTO {

    @NotBlank(message = "Id do produto deve ser enviado")
    private String productId;
    @Min(1)
    private BigDecimal newPrice;
    @NotNull(message = "Data de inicio deve ser enviada")
    private LocalDateTime start;
    @NotNull(message = "Data final deve ser enviada")
    private LocalDateTime end;
}
