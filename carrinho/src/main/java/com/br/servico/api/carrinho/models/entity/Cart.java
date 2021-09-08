package com.br.servico.api.carrinho.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Document(collection = "cart")
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    private String id;
    private User user;
    private List<Product> products;
    private int qtdProduct;
    private BigDecimal totalAmount;

    private Cart(User user) {
        this.user = user;
        this.products = Collections.emptyList();
    }

    public static Cart of(User user) {
        return new Cart(user);
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalAmount = this.totalAmount.add(product.getTotalAmount());
        this.qtdProduct = this.qtdProduct + product.getQtd();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void removeProduct(Product product, int qtd) {
        int index = this.products.indexOf(product);
        this.products.get(index).removeQtd(qtd);
    }

    public void removeProduct(List<Product> products) {
        this.products.removeAll(products);
    }
}
