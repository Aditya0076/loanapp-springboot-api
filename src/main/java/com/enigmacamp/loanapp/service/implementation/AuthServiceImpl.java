package com.enigmacamp.loanapp.service.implementation;

import com.enigmacamp.loanapp.model.dto.request.AuthRequest;
import com.enigmacamp.loanapp.model.dto.response.AuthRespon;
import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.enigmacamp.loanapp.model.entity.Role;
import com.enigmacamp.loanapp.repository.AppUsersRepository;
import com.enigmacamp.loanapp.security.JWTUtil;
import com.enigmacamp.loanapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AppUsersRepository appUsersRepository;
    private final AppUsers appUsers;

    @Override
    public AuthRespon login(AuthRequest<String> request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Set authentication in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Retrieve authenticated user details
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        AppUsers appUser = userDetails.getAppUser();

        // Generate JWT token
        String token = jwtUtil.generateToken(appUser);

        // Map roles to the response
        Role role = appUser.getRoles().stream().findFirst().orElse(null);

        return AuthRespon.builder()
                .token(token)
                .role(role)  // Assuming role is a string in AuthRespon
                .build();
    }

    @Override
    public AuthRespon getUser(String id) {
        AppUsers appUser = appUsersRepository.findById(id).get();
        String token = jwtUtil.generateToken(appUser);
        Role role = appUser.getRoles().stream().findFirst().orElse(null);
        return AuthRespon.builder()
                .token(token)
                .role(role)
                .build();

    }
}
