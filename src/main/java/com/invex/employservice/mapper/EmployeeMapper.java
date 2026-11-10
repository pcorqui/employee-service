package com.invex.employservice.mapper;

import com.invex.employservice.dto.EmployeeRequestDTO;
import com.invex.employservice.dto.EmployeeResponseDTO;
import com.invex.employservice.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDTO dto) {
        return Employee.builder()
                .firstName(dto.firstName())
                .middleName(dto.middleName())
                .lastName(dto.lastName())
                .secondLastName(dto.secondLastName())
                .age(dto.age())
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .active(dto.active() != null ? dto.active() : true)
                .build();
    }

    public EmployeeResponseDTO toResponse(Employee entity) {
        return EmployeeResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .secondLastName(entity.getSecondLastName())
                .age(entity.getAge())
                .gender(entity.getGender())
                .birthDate(entity.getBirthDate())
                .createdAt(entity.getCreatedAt())
                .active(entity.getActive())
                .build();
    }
}

