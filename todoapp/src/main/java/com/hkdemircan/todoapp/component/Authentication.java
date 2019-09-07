package com.hkdemircan.todoapp.component;

public interface Authentication {

    public org.springframework.security.core.Authentication getAuthentication();

    public String getUsername();

    public boolean isAuthenticated();

    public boolean hasRole(Long roleId);

}
