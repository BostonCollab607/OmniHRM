package com.example.performance_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String companyName;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String dateOfBirth;
    private List<String> roles;
    private String team;


}