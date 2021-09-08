package com.br.servico.api.produtos.repositorys;

import com.br.servico.api.produtos.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, String> {
}
