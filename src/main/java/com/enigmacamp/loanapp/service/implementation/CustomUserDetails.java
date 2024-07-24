package com.enigmacamp.loanapp.service.implementation;

import com.enigmacamp.loanapp.model.entity.AppUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetails implements UserDetails {

    private final AppUsers appUsers;

    public CustomUserDetails(AppUsers appUsers) {
        this.appUsers = appUsers;
    }

    public AppUsers getAppUser() {
        return appUsers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUsers.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return appUsers.getPassword();
    }

    @Override
    public String getUsername() {
        return appUsers.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static CustomUserDetails build(AppUsers appUsers) {
        return new CustomUserDetails(appUsers);
    }
}
