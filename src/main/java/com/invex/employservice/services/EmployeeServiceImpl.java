package com.invex.employservice.services;

import com.invex.employservice.constanst.AppConstants;
import com.invex.employservice.dto.EmployeeRequestDTO;
import com.invex.employservice.dto.EmployeeResponseDTO;
import com.invex.employservice.entity.Employee;
import com.invex.employservice.exception.ResourceNotFoundException;
import com.invex.employservice.mapper.EmployeeMapper;
import com.invex.employservice.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND + id));
    }

    @Transactional
    @Override
    public List<EmployeeResponseDTO> saveAll(List<EmployeeRequestDTO> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new IllegalArgumentException(AppConstants.EMPTY_LIST_ERROR);
        }
        List<Employee> entities = requests.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        return repository.saveAll(entities).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EmployeeResponseDTO update(Long id, EmployeeRequestDTO request) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND + id));

        // Actualización parcial o total usando programación funcional (Optional)
        Optional.ofNullable(request.firstName()).ifPresent(employee::setFirstName);
        Optional.ofNullable(request.middleName()).ifPresent(employee::setMiddleName);
        Optional.ofNullable(request.lastName()).ifPresent(employee::setLastName);
        Optional.ofNullable(request.secondLastName()).ifPresent(employee::setSecondLastName);
        Optional.ofNullable(request.age()).ifPresent(employee::setAge);
        Optional.ofNullable(request.gender()).ifPresent(employee::setGender);
        Optional.ofNullable(request.birthDate()).ifPresent(employee::setBirthDate);
        Optional.ofNullable(request.active()).ifPresent(employee::setActive);

        return mapper.toResponse(repository.save(employee));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(AppConstants.EMPLOYEE_NOT_FOUND + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeResponseDTO> searchByName(String name) {
        return repository.findByFirstNameContainingIgnoreCase(name).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}
