package com.br.servico.api.carrinho.services.impl;

import com.br.servico.api.carrinho.models.entity.User;
import com.br.servico.api.carrinho.models.response.ProductDataRestDTO;
import com.br.servico.api.carrinho.services.IProductServiceRest;
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
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceRest implements IProductServiceRest {

    @Autowired
    private HttpServletRequest request;

    @Value("${url.service.get.product}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductDataRestDTO getProduct(String id) {
        HttpEntityUtils<User> httpEntity = new HttpEntityUtils<>();
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", id);
            ResponseEntity<ProductDataRestDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity.createHttpEntity(request), ProductDataRestDTO.class, params);
            return response.getBody();
        } catch (HttpClientErrorException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
