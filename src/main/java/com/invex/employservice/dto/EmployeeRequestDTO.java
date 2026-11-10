package com.invex.employservice.dto;


import com.invex.employservice.constanst.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;


import java.time.LocalDate;

public record EmployeeRequestDTO (

    @NotBlank(message = "El primer nombre es obligatorio")
    String firstName,

    String middleName,

    @NotBlank(message = "El apellido paterno es obligatorio")
    String lastName,

    String secondLastName,

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 100, message = "La edad máxima es 100 años")
    Integer age,

    @NotBlank(message = "El sexo es obligatorio")
    String gender,

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.DATE_FORMAT)
    LocalDate birthDate,

    Boolean active
){}
