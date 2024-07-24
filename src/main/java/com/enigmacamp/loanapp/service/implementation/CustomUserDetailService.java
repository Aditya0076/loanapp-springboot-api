package com.enigmacamp.loanapp.service.implementation;

import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.enigmacamp.loanapp.repository.AppUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final AppUsersRepository appUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUsers appUsers = appUsersRepository.findByEmail(email).orElse(null);
        if (appUsers == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(appUsers);
    }
}
