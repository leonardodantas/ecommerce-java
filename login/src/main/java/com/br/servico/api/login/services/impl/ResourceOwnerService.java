package com.br.servico.api.login.services.impl;

import com.br.servico.api.login.models.entity.User;
import com.br.servico.api.login.models.response.ResourceOwnerResponseDTO;
import com.br.servico.api.login.services.IResourceOwnerService;
import com.br.servico.api.login.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceOwnerService implements IResourceOwnerService {

    @Autowired
    private IUserService userService;

    @Override
    public ResourceOwnerResponseDTO findResourceOwner(String id) {
        User user = userService.findById(id);
        return ResourceOwnerResponseDTO.of(user);
    }
}
