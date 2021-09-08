package com.br.servico.api.produtos.services;

import com.br.servico.api.produtos.models.request.ProductRequestDTO;
import com.br.servico.api.produtos.models.response.PageProductsResponseDTO;
import com.br.servico.api.produtos.models.response.ProductResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IproductService {

    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProdutId(String id);
    PageProductsResponseDTO getAllProduts(Pageable pageable);
}
