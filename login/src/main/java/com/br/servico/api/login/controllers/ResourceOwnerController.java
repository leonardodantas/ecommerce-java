package com.br.servico.api.login.controllers;

import com.br.servico.api.login.models.response.ResourceOwnerResponseDTO;
import com.br.servico.api.login.services.IResourceOwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@Api(tags = "Resource Owner")
@RestController
@RequestMapping("/v1")
public class ResourceOwnerController {

    @Autowired
    private IResourceOwnerService resourceOwnerService;

    @GetMapping("/resource-owner/{id}")
    @ApiOperation(tags = "Resource Owner", value = "Recuperar Resource Owner")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Resource Owner recuperado", response = ResourceOwnerResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<ResourceOwnerResponseDTO> findResourceOwner(@PathVariable String id){
        ResourceOwnerResponseDTO response = resourceOwnerService.findResourceOwner(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
