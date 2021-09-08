package com.br.servico.api.produtos.config.security.services;

import com.br.servico.api.produtos.config.security.dto.ResourceOwnerResponseDTO;

public interface IResouserOwnerService {
    ResourceOwnerResponseDTO findResouserOwnerById(String userId);
}
