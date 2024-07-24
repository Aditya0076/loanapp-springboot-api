package com.enigmacamp.loanapp.service;

import com.enigmacamp.loanapp.model.dto.response.AvatarResponse;
import com.enigmacamp.loanapp.model.dto.response.CustomerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface PhotoService {
    AvatarResponse uploadPhotoCustomerById(String id, MultipartFile file) throws  IOException;

    Optional <AvatarResponse> getPhotoCustomerById(String id);
    CustomerResponse updatePhotoCustomerById(String id, MultipartFile file) throws IOException;
}
