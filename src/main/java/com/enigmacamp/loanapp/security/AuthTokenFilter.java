package com.enigmacamp.loanapp.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.enigmacamp.loanapp.service.implementation.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                String clientToken = headerAuth.substring(7);

                if (jwtUtil.validateToken(clientToken)) {
                    Map<String, String> userInfo = jwtUtil.getUserInfoByToken(clientToken);

                    // Logging untuk debugging
                    System.out.println("Token Valid, User Info: " + userInfo);

                    UserDetails user = userService.loadUserByUsername(userInfo.get("userId"));
                    // Logging untuk debugging
                    System.out.println("Loaded User: " + user);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    request.setAttribute("userId", userInfo.get("userId"));
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                    return;
                }
            }
        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
