package com.enigmacamp.loanapp.service;


import com.enigmacamp.loanapp.model.dto.request.AdminResquest;
import com.enigmacamp.loanapp.model.dto.response.AdminResponse;

import java.util.List;

public interface AdminService {
    AdminResponse addAdmin(AdminResquest adminResquest);
    List <AdminResponse> getAllAdmins();
    AdminResponse getAdminByFirstName(String firstName);
}
