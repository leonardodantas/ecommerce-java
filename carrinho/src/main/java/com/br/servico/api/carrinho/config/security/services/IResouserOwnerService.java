package com.br.servico.api.carrinho.config.security.services;

import com.br.servico.api.carrinho.config.security.dto.ResourceOwnerResponseDTO;

public interface IResouserOwnerService {
    ResourceOwnerResponseDTO findResouserOwnerById(String userId);
}
