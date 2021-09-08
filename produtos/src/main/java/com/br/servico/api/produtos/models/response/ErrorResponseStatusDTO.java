package com.br.servico.api.produtos.models.response;

import lombok.Getter;

@Getter
public class ErrorResponseStatusDTO {

    private String uuid;
    private String message;
    private String date;

}