package com.enigmacamp.loanapp.service;


import com.enigmacamp.loanapp.model.dto.request.StaffRequest;
import com.enigmacamp.loanapp.model.dto.response.StaffResponse;

import java.util.List;

public interface StaffService {

    StaffResponse createStaff(StaffRequest staffRequest);

    List<StaffResponse> getAllStaffs();
}
