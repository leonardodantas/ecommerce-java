package com.br.servico.api.produtos.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageResponse {

    private final int page;
    private final int size;
    private final long totalElements;


}
