package com.br.servico.api.carrinho.services.impl;

import com.br.servico.api.carrinho.models.entity.Product;
import com.br.servico.api.carrinho.models.request.ProductRequestDTO;
import com.br.servico.api.carrinho.models.response.ProductDataRestDTO;
import com.br.servico.api.carrinho.services.IProductService;
import com.br.servico.api.carrinho.services.IProductServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductServiceRest productServiceRest;

    @Override
    public Product findProduct(ProductRequestDTO productRequestDTO){
        ProductDataRestDTO productDataRestDTO = productServiceRest.getProduct(productRequestDTO.getId());
        return Product.of(productDataRestDTO, productRequestDTO);
    }
}
