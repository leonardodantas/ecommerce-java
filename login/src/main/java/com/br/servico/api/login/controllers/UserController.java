package com.br.servico.api.login.controllers;

import com.br.servico.api.login.models.request.UserRequestDTO;
import com.br.servico.api.login.models.response.ErrorResponseDTO;
import com.br.servico.api.login.models.response.UserResponseDTO;
import com.br.servico.api.login.services.IUserService;
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

@Api(tags = "Usuarios")
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    @ApiOperation(value = "Criar um novo usuario", code = 201)
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Usuario criado", response = UserResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO body){
        UserResponseDTO response = userService.createUser(body);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Recupera um usuario atraves de seu token")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Usuario recuperado", response = UserResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Requisição negada", response = ErrorResponseDTO.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Não autorizado", response = ErrorResponseDTO.class)
            })
    public ResponseEntity<UserResponseDTO> getUser(@RequestHeader(value = "Authorization") String authorization){
        UserResponseDTO response = userService.getUser();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
