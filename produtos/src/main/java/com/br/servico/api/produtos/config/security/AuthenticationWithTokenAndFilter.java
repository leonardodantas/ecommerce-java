package com.br.servico.api.produtos.config.security;

import com.br.servico.api.produtos.config.security.dto.ResourceOwnerResponseDTO;
import com.br.servico.api.produtos.config.security.services.IResouserOwnerService;
import com.br.servico.api.produtos.config.security.services.ITokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationWithTokenAndFilter extends OncePerRequestFilter {

    private final IResouserOwnerService resouserOwnerService;
    private final ITokenService tokenService;

    private AuthenticationWithTokenAndFilter(IResouserOwnerService resouserOwnerService, ITokenService tokenService) {
        this.resouserOwnerService = resouserOwnerService;
        this.tokenService = tokenService;
    }

    public static AuthenticationWithTokenAndFilter of(IResouserOwnerService resouserOwnerService, ITokenService tokenService) {
        return new AuthenticationWithTokenAndFilter(resouserOwnerService, tokenService);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenService.getToken(request);
        boolean tokenValid = tokenService.isTokenValid(token);

        if (tokenValid) {
            authenticationUser(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticationUser(String token) {
        String userId = tokenService.getIdUser(token);
        ResourceOwnerResponseDTO resourceOwner = resouserOwnerService.findResouserOwnerById(userId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(resourceOwner, null, resourceOwner.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
