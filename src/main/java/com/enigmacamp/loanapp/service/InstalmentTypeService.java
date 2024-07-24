package com.enigmacamp.loanapp.service;


import com.enigmacamp.loanapp.model.dto.request.InstalmentTypeRequest;
import com.enigmacamp.loanapp.model.dto.response.InstalmentTypeResponse;

public interface InstalmentTypeService {
    //InstalmentType createInstalmentType(InstalmentType.EInstalmentType instalmentType);
    InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest instalmentTypeRequest);

}
