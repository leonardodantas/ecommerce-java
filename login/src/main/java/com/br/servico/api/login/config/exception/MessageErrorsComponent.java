package com.br.servico.api.login.config.exception;

import com.br.servico.api.login.models.dto.DetailErrorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageErrorsComponent {

    @Autowired
    private MessageSource messageSource;

    public List<DetailErrorResponseDTO> createListErrors(List<FieldError> fieldErrors){
        return fieldErrors.stream()
                .map(this::createError)
                .collect(Collectors.toUnmodifiableList());
    }

    private DetailErrorResponseDTO createError(FieldError field){
        String message = messageSource.getMessage(field, LocaleContextHolder.getLocale());
        return DetailErrorResponseDTO.of(field.getField(), message);
    }
}
