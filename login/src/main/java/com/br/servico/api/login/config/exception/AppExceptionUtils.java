package com.br.servico.api.login.config.exception;

import com.br.servico.api.login.models.dto.DetailErrorResponseDTO;
import com.br.servico.api.login.models.response.ErrorResponseDTO;
import com.br.servico.api.login.models.response.ErrorResponseStatusDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@ControllerAdvice
public class AppExceptionUtils {

    @Autowired
    private MessageErrorsComponent messageErrors;

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<?> reponseStatusExceptionTratament(ResponseStatusException error) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponseDTO errorResponseDTO;
        try {
            ErrorResponseStatusDTO errorResponseStatus = mapper.readValue(error.getReason(), ErrorResponseStatusDTO.class);
            errorResponseDTO = ErrorResponseDTO.of(errorResponseStatus, error.getStatus());
        } catch (JsonProcessingException exception) {
            errorResponseDTO = ErrorResponseDTO.of(error.getStatus(), error.getReason());
        }
        return new ResponseEntity<>(errorResponseDTO, new HttpHeaders(), error.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> reponseStatusExceptionTratament(MethodArgumentNotValidException error) {
        List<FieldError> fieldErrors = error.getBindingResult().getFieldErrors();
        List<DetailErrorResponseDTO> listErrors = messageErrors.createListErrors(fieldErrors);
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.of(listErrors);
        return new ResponseEntity<>(errorResponseDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}