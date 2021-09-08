package com.br.servico.api.carrinho.services;

import com.br.servico.api.carrinho.models.entity.Product;
import com.br.servico.api.carrinho.models.request.ProductRequestDTO;

public interface IProductService {

    Product findProduct(ProductRequestDTO productRequestDTO);
}
