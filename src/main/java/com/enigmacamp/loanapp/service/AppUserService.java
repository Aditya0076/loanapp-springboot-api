package com.enigmacamp.loanapp.service;


import com.enigmacamp.loanapp.model.dto.request.AppUserRequest;
import com.enigmacamp.loanapp.model.dto.response.AppUserResponse;

public interface AppUserService{
    AppUserResponse createAppUser(AppUserRequest appUserRequest);

}
