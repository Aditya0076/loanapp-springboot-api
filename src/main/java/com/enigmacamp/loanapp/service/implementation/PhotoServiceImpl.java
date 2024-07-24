package com.enigmacamp.loanapp.service.implementation;


import com.enigmacamp.loanapp.model.dto.response.AvatarResponse;
import com.enigmacamp.loanapp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanapp.model.entity.Customer;
import com.enigmacamp.loanapp.model.entity.Photo;
import com.enigmacamp.loanapp.repository.CustomerRepository;
import com.enigmacamp.loanapp.repository.PhotoRepository;
import com.enigmacamp.loanapp.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AvatarResponse uploadPhotoCustomerById(String id, MultipartFile file) throws IOException {
        Photo photo = Photo.builder()
                .customer(customerRepository.findById(id).get())
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        photoRepository.save(photo);
        return AvatarResponse.builder()
                .id(photo.getId())
                .name(photo.getName())
                .type(photo.getType())
                .data(photo.getData())
                .build();
    }

    @Override
    public Optional<AvatarResponse> getPhotoCustomerById(String id) {
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()) {
            Customer customer = customerRepository.findById(photo.get().getCustomer().getId()).get();
            CustomerResponse customerResponse = CustomerResponse.builder()
                    .id(customer.getId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .dateOfBirth(customer.getDateOfBirth())
                    .phone(customer.getPhone())
                    .status(customer.getStatus())
                    .user(customer.getUser())
                    .build();

            return Optional.of(AvatarResponse.builder()
                    .id(photo.get().getId())
                    .name(photo.get().getName())
                    .type(photo.get().getType())
                    .data(photo.get().getData())
                    .customer(customerResponse)
                    .build());
        }
        return Optional.empty();
    }



    @Override
    public CustomerResponse updatePhotoCustomerById(String id, MultipartFile file) throws IOException {
        return null;
    }
}
