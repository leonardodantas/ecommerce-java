package com.br.servico.api.produtos.repositorys;

import com.br.servico.api.produtos.models.entity.PriceHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPriceHistoryRepository extends JpaRepository<PriceHistory, String> {

    Optional<PriceHistory> findTopByProductIdOrderByStartDesc(String id);
    Page<PriceHistory> findAllByProductIdOrderByStartDesc(String id, Pageable pageable);
}
