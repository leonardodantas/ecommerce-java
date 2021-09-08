package com.br.servico.api.login.models.request;

public enum RoleRequest {

    ADMIN("ROLE_ADMIN"), MANAGER("ROLE_MANAGER"), USER("ROLE_USER");

    private String role;

    RoleRequest(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
