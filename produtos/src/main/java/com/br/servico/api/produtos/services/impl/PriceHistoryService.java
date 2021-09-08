package com.br.servico.api.produtos.services.impl;

import com.br.servico.api.produtos.models.entity.PriceHistory;
import com.br.servico.api.produtos.models.response.PagePriceHistoryResponseDTO;
import com.br.servico.api.produtos.models.response.PriceProductResponseDTO;
import com.br.servico.api.produtos.models.response.ProductResponseDTO;
import com.br.servico.api.produtos.repositorys.IPriceHistoryRepository;
import com.br.servico.api.produtos.services.IPriceHistoryService;
import com.br.servico.api.produtos.services.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class PriceHistoryService implements IPriceHistoryService {

    @Autowired
    private IproductService productService;

    @Autowired
    private IPriceHistoryRepository priceHistoryRepository;

    private static final String PRICE_NOT_FOUND = "Nenhum registro de preço encontrado";

    @Override
    public PriceProductResponseDTO updatePriceProduct(String idProduct, BigDecimal price) {
        ProductResponseDTO product = productService.getProdutId(idProduct);
        PriceHistory priceHistory = findLastPriceHistory(product);
        updateAndSave(priceHistory);
        PriceHistory priceHistorySave = createNewAndSave(price, product);
        return PriceProductResponseDTO.of(priceHistorySave);
    }

    @Override
    public PagePriceHistoryResponseDTO getAllProductPrice(String id, PageRequest pageable) {
        Page<PriceHistory> priceHistoryList = priceHistoryRepository.findAllByProductIdOrderByStartDesc(id, pageable);
        return PagePriceHistoryResponseDTO.of(priceHistoryList);
    }

    @Override
    public PriceProductResponseDTO getLastPrice(String productId) {
        PriceHistory priceHistory = this.getLastPriceHistoryWithProductId(productId);
        return PriceProductResponseDTO.of(priceHistory);
    }

    private PriceHistory getLastPriceHistoryWithProductId(String productId){
        ProductResponseDTO product = productService.getProdutId(productId);
        return findLastPriceHistory(product);
    }

    private void updateAndSave(PriceHistory priceHistory) {
        priceHistory.updatePriceEnd();
        savePriceHistoryInDataBase(priceHistory);
    }

    private PriceHistory createNewAndSave(BigDecimal price, ProductResponseDTO product) {
        PriceHistory priceHistory = PriceHistory.of(product.getId(), price);
        return priceHistoryRepository.save(priceHistory);
    }

    private PriceHistory findLastPriceHistory(ProductResponseDTO product) {
        try {
            return priceHistoryRepository
                    .findTopByProductIdOrderByStartDesc(product.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRICE_NOT_FOUND));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private PriceHistory savePriceHistoryInDataBase(PriceHistory priceHistory){
        try {
            return priceHistoryRepository.save(priceHistory);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
