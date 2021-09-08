package com.br.servico.api.carrinho.services;

import com.br.servico.api.carrinho.models.response.ProductDataRestDTO;

public interface IProductServiceRest {
    ProductDataRestDTO getProduct(String id);
}
