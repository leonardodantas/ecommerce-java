package com.br.servico.api.produtos.services.impl;

import com.br.servico.api.produtos.models.entity.Promotion;
import com.br.servico.api.produtos.models.request.PromotionPriceRequestDTO;
import com.br.servico.api.produtos.models.response.PagePromotionResponseDTO;
import com.br.servico.api.produtos.models.response.PriceProductResponseDTO;
import com.br.servico.api.produtos.models.response.ProductResponseDTO;
import com.br.servico.api.produtos.models.response.PromotionResponseDTO;
import com.br.servico.api.produtos.repositorys.IPromotionRepository;
import com.br.servico.api.produtos.services.IPriceHistoryService;
import com.br.servico.api.produtos.services.IPromotionService;
import com.br.servico.api.produtos.services.IproductService;
import com.br.servico.api.produtos.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {

    @Autowired
    private IPromotionRepository promotionRepository;

    @Autowired
    private IproductService productService;

    @Autowired
    private IPriceHistoryService priceHistoryService;

    private static final String PERIOD_PROMOTION_ERROR = "Periodo escolhido para a promoção não disponivel";

    @Override
    public PromotionResponseDTO createPromotion(PromotionPriceRequestDTO promotionPriceRequestDTO) {
        validatePeriodPromotion(promotionPriceRequestDTO);
        Promotion promotion = createPromotionToSave(promotionPriceRequestDTO);
        Promotion promotionSave = this.savePromotionInDataBase(promotion);
        return PromotionResponseDTO.of(promotionSave);
    }

    @Override
    public PagePromotionResponseDTO getAllPromotion(String idProduct, Pageable pageable) {
        Page<Promotion> promotionPage = this.findAllPromotionPageInDataBase(idProduct, pageable);
        return PagePromotionResponseDTO.of(promotionPage);
    }

    private Page<Promotion> findAllPromotionPageInDataBase(String idProduct, Pageable pageable){
        try {
            return promotionRepository.findAllByProductId(idProduct, pageable);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private Promotion createPromotionToSave(PromotionPriceRequestDTO promotionPriceRequestDTO) {
        ProductResponseDTO product = productService.getProdutId(promotionPriceRequestDTO.getProductId());
        PriceProductResponseDTO priceProductResponseDTO = priceHistoryService.getLastPrice(product.getId());
        Promotion promotion = Promotion.of(promotionPriceRequestDTO, priceProductResponseDTO);
        return promotion;
    }

    private void validatePeriodPromotion(PromotionPriceRequestDTO promotionPriceRequestDTO) {
        validateDates(promotionPriceRequestDTO);

        List<Promotion> promotions = promotionRepository.findAllByProductId(promotionPriceRequestDTO.getProductId());
        LocalDateTime fistPeriod = promotionPriceRequestDTO.getStart();
        LocalDateTime endPeriod = promotionPriceRequestDTO.getEnd();

        promotions
                .stream()
                .filter(promotion ->
                                DateUtils.validateDateBetweenPeriod(fistPeriod, promotion.getStart(), promotion.getEnd()) ||
                                DateUtils.validateDateBetweenPeriod(endPeriod, promotion.getStart(), promotion.getEnd()) ||
                                DateUtils.validateDateBeforePeriod(fistPeriod, promotion.getStart(), promotion.getEnd())
        ).findAny().ifPresent(promotion -> {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, PERIOD_PROMOTION_ERROR);
        });
    }

    private void validateDates(PromotionPriceRequestDTO promotionPriceRequestDTO) {
        DateUtils.validateStartAfterEnd(promotionPriceRequestDTO.getStart(), promotionPriceRequestDTO.getStart());
        DateUtils.validateStartBeforeNow(promotionPriceRequestDTO.getStart());
        DateUtils.validateDateEquals(promotionPriceRequestDTO.getStart(), promotionPriceRequestDTO.getEnd());
    }

    private Promotion savePromotionInDataBase(Promotion promotion){
        try {
            return promotionRepository.save(promotion);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
