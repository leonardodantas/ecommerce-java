package com.br.servico.api.carrinho.services.impl;

import com.br.servico.api.carrinho.config.security.dto.ResourceOwnerResponseDTO;
import com.br.servico.api.carrinho.config.security.services.ITokenService;
import com.br.servico.api.carrinho.models.entity.User;
import com.br.servico.api.carrinho.services.IUserServiceRest;
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

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceRestRest implements IUserServiceRest {

    @Value("${url.service.get.user}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public User getUser() {
        HttpEntityUtils<User> httpEntity = new HttpEntityUtils<>();
        try {
            ResponseEntity<User> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity.createHttpEntity(request), User.class);
            return response.getBody();
        } catch (HttpClientErrorException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
