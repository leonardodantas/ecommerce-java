package com.br.servico.api.produtos.models.entity;

import com.br.servico.api.produtos.models.request.TagRequestDTO;
import com.br.servico.api.produtos.models.request.TagsRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Locale;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "tags")
@Getter
public class Tags {

    @Id
    private String id;
    @Column(length = 6, unique = true)
    private String tag;
    @Column(length = 120, name = "descricao")
    private String description;

    private Tags(String idTag) {
        this.id = idTag;
    }

    private Tags(TagRequestDTO tagRequestDTO) {
        this.id = UUID.randomUUID().toString();
        this.description = tagRequestDTO.getDescription();
        this.tag = tagRequestDTO.getTag().toUpperCase(Locale.ROOT);
    }

    public static Tags of(TagsRequestDTO tagsRequestDTO) {
        return new Tags(tagsRequestDTO.getIdTag());
    }

    public static Tags of(TagRequestDTO tagsRequestDTO) {
        return new Tags(tagsRequestDTO);
    }
}
