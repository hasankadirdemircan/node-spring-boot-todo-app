package com.hkdemircan.todoapp.component;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationImpl implements Authentication{

    @Override
    public org.springframework.security.core.Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getUsername() {
        if (isAuthenticated())
            return getAuthentication().getName();
        return null;

    }

    @Override
    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    @Override
    public boolean hasRole(Long roleId) {
        if (isAuthenticated()) {
            return getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(roleId.toString()));
        }
        return false;

    }
}
