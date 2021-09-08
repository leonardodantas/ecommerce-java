package com.br.servico.api.produtos.services.impl;

import com.br.servico.api.produtos.models.entity.Product;
import com.br.servico.api.produtos.models.request.ProductRequestDTO;
import com.br.servico.api.produtos.models.request.TagsRequestDTO;
import com.br.servico.api.produtos.models.response.PageProductsResponseDTO;
import com.br.servico.api.produtos.models.response.ProductResponseDTO;
import com.br.servico.api.produtos.repositorys.IProductRepository;
import com.br.servico.api.produtos.services.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IproductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private TagsService tagsService;

    private static final String PRODUTO_NOT_FOUND = "Produto não encontrado";

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        List<TagsRequestDTO> tagsRequestDTOS = tagsService.verifyIdTags(productRequestDTO.getTags());
        Product product = Product.of(productRequestDTO, tagsRequestDTOS);
        Product productSave = this.saveProductInDataBase(product);
        return ProductResponseDTO.of(productSave);
    }

    @Override
    public ProductResponseDTO getProdutId(String id) {
        Product product = getProductIdInDataBase(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUTO_NOT_FOUND));
        return ProductResponseDTO.of(product);
    }

    @Override
    public PageProductsResponseDTO getAllProduts(Pageable pageable) {
        Page<Product> productPage = findAllProductsPageInDataBase(pageable);
        return PageProductsResponseDTO.of(productPage);
    }

    private Page<Product> findAllProductsPageInDataBase(Pageable pageable){
        try {
            return productRepository.findAll(pageable);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private Optional<Product> getProductIdInDataBase(String id){
        try {
            return productRepository.findById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private Product saveProductInDataBase(Product product){
        try {
            return productRepository.save(product);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
