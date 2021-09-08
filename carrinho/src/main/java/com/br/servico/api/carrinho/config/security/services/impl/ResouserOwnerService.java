package com.br.servico.api.carrinho.config.security.services.impl;

import com.br.servico.api.carrinho.config.security.dto.ResourceOwnerResponseDTO;
import com.br.servico.api.carrinho.config.security.services.IResouserOwnerService;
import com.br.servico.api.carrinho.utils.HttpEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResouserOwnerService implements IResouserOwnerService {

    @Value("${url.service.authentication}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResourceOwnerResponseDTO findResouserOwnerById(String userId) {
        HttpEntityUtils<ResourceOwnerResponseDTO> httpEntity = new HttpEntityUtils<>();
        try {
            Map<String, String> param = new HashMap<>();
            param.put("id", userId);

            ResponseEntity<ResourceOwnerResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity.createHttpEntity(), ResourceOwnerResponseDTO.class, param);
            return response.getBody();
        } catch (HttpClientErrorException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
