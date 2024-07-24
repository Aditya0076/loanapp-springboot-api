package com.enigmacamp.loanapp.service.implementation;

import com.enigmacamp.loanapp.model.dto.request.InstalmentTypeRequest;
import com.enigmacamp.loanapp.model.dto.response.InstalmentTypeResponse;
import com.enigmacamp.loanapp.model.entity.InstalmentType;
import com.enigmacamp.loanapp.repository.InstalmentTypeRepository;
import com.enigmacamp.loanapp.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    private final InstalmentTypeRepository instalmentTypeRepository;

    @Override
    public InstalmentTypeResponse createInstalmentType(InstalmentTypeRequest instalmentTypeRequest) {
        InstalmentType.EInstalmentType instalmentType = InstalmentType.EInstalmentType.valueOf(String.valueOf(instalmentTypeRequest.getInstalmentType()));
        Optional <InstalmentType> instalmentTypeOptional = instalmentTypeRepository.findByInstalmentType(instalmentType);
        if (instalmentTypeOptional.isPresent()) {
            return InstalmentTypeResponse.builder()
                    .id(instalmentTypeOptional.get().getId())
                    .instalmentType(InstalmentType.EInstalmentType.valueOf(instalmentTypeOptional.get().getInstalmentType().toValue()))
                    .build();
        }
        InstalmentType newInstalmentType = InstalmentType.builder()
                .instalmentType(instalmentType)
                .build();
        return InstalmentTypeResponse.builder()
                .id(newInstalmentType.getId())
                .instalmentType(InstalmentType.EInstalmentType.valueOf(newInstalmentType.getInstalmentType().toValue()))
                .build();
    }
}
