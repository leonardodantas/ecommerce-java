package com.br.servico.api.login.controllers;

import com.br.servico.api.login.models.request.UserLoginRequestDTO;
import com.br.servico.api.login.models.response.TokenResponseDTO;
import com.br.servico.api.login.services.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.HttpURLConnection;

@Api(tags = "Login")
@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    @ApiOperation(tags = "Login", value = "Login do usuario")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Login user", response = TokenResponseDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<TokenResponseDTO> loginUser(@Valid @RequestBody UserLoginRequestDTO body){
        TokenResponseDTO response = loginService.loginUser(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
