package com.invex.employservice.services;

import com.invex.employservice.dto.EmployeeRequestDTO;
import com.invex.employservice.dto.EmployeeResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {
    @Transactional(readOnly = true)
    List<EmployeeResponseDTO> findAll();

    @Transactional(readOnly = true)
    EmployeeResponseDTO findById(Long id);

    @Transactional
    List<EmployeeResponseDTO> saveAll(List<EmployeeRequestDTO> requests);

    @Transactional
    EmployeeResponseDTO update(Long id, EmployeeRequestDTO request);

    @Transactional
    void delete(Long id);

    @Transactional(readOnly = true)
    List<EmployeeResponseDTO> searchByName(String name);
}
