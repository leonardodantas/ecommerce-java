package com.br.servico.api.produtos.models.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DataResponse<T> {
    
    private T data;
}
