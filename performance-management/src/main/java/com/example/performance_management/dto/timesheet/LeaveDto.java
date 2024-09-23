package com.example.performance_management.dto.timesheet;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LeaveDto {
    public static String SICK_LEAVE = "Sick_Leave";
    public static String CASUAL_LEAVE = "Casual_Leave";
    public static String EARNED_LEAVE = "Earned_Leave";
    public String userName;
    public List<String> date;
    public Float leaveDuration;
    public String leaveType;
}
