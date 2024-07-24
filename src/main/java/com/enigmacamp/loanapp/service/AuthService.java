package com.enigmacamp.loanapp.service;


import com.enigmacamp.loanapp.model.dto.request.AuthRequest;
import com.enigmacamp.loanapp.model.dto.response.AuthRespon;

public interface AuthService {
    AuthRespon login(AuthRequest<String> request);

    AuthRespon getUser(String id);
}
