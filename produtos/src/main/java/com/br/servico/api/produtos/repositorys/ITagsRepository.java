package com.br.servico.api.produtos.repositorys;

import com.br.servico.api.produtos.models.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITagsRepository extends JpaRepository<Tags, String> {
}
