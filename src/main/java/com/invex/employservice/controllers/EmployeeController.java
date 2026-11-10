package com.invex.employservice.controllers;

import com.invex.employservice.dto.EmployeeRequestDTO;
import com.invex.employservice.dto.EmployeeResponseDTO;
import com.invex.employservice.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Validated
@Tag(name = "Empleados", description = "API REST para la gestión de empleados")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Listar todos los empleados")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de un empleado por ID")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Insertar uno o varios empleados")
    public ResponseEntity<List<EmployeeResponseDTO>> createEmployees(
            @RequestBody @Valid List<EmployeeRequestDTO> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveAll(requests));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar total o parcialmente un empleado")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDTO request) {
        return ResponseEntity.ok(employeeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado por ID")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar empleados por coincidencia parcial en el primer nombre")
    public ResponseEntity<List<EmployeeResponseDTO>> searchEmployees(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }
}

