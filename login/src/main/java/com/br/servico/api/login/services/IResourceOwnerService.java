package com.br.servico.api.login.services;

import com.br.servico.api.login.models.response.ResourceOwnerResponseDTO;

public interface IResourceOwnerService {
    ResourceOwnerResponseDTO findResourceOwner(String id);
}
