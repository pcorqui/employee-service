package com.invex.employservice;

import com.invex.employservice.dto.EmployeeResponseDTO;
import com.invex.employservice.entity.Employee;
import com.invex.employservice.exception.ResourceNotFoundException;
import com.invex.employservice.mapper.EmployeeMapper;
import com.invex.employservice.repositories.EmployeeRepository;
import com.invex.employservice.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @Spy
    private EmployeeMapper mapper = new EmployeeMapper();

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void findById_WhenExists_ReturnsEmployee() {
        Employee emp = Employee.builder()
                .id(1L)
                .firstName("Carlos")
                .lastName("Pérez")
                .age(30)
                .gender("M")
                .birthDate(LocalDate.of(1994, 5, 15))
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(emp));

        EmployeeResponseDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Carlos", result.getFirstName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findById_WhenNotExists_ThrowsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }
}
