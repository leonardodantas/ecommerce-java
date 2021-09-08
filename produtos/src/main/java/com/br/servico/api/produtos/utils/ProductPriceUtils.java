package com.br.servico.api.produtos.utils;


import com.br.servico.api.produtos.models.entity.Product;
import com.br.servico.api.produtos.models.entity.Promotion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ProductPriceUtils {

    private ProductPriceUtils(){}

    public static double getdiscountPercentage(BigDecimal oldPrice, BigDecimal newPrice){
        double percentage = newPrice.multiply(new BigDecimal(100)).divide(oldPrice).doubleValue();
        return 100 - percentage;
    }

    public static BigDecimal getPrice(Product product) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Promotion> promotionExist = product.getPromotions().stream().
                filter(promotion -> now.isAfter(promotion.getStart()) && now.isBefore(promotion.getEnd())).
                findFirst();
        if(promotionExist.isPresent()) {
            return promotionExist.get().getPrice();
        }
        return product.getPrice();
    }
}
