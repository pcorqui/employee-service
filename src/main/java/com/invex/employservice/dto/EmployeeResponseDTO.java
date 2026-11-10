package com.invex.employservice.dto;


import com.invex.employservice.constanst.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private Integer age;
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.DATE_FORMAT)
    private LocalDate birthDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    private Boolean active;
}
