package com.br.servico.api.produtos.repositorys;

import com.br.servico.api.produtos.models.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPromotionRepository extends JpaRepository<Promotion, String> {
    List<Promotion> findAllByProductId(String productId);
    Page<Promotion> findAllByProductId(String idProduct, Pageable pageable);
}
