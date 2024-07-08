package com.example.performance_management.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoalDto {

    private String username;
    private String goal;
    private String empInput;
    private String mgrFeedback;

}