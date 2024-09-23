package com.example.performance_management.mapper;

import com.example.performance_management.dto.timesheet.LeaveDto;
import com.example.performance_management.entity.timesheet.Leave;

public class LeaveMapper {
    public Leave convertToEntity(LeaveDto leaveDto) {
        Leave leave = new Leave();
        leave.setLeaveDuration(leaveDto.getLeaveDuration());
        leave.setLeaveType(leaveDto.getLeaveType());
        leave.setDate(leaveDto.getDate());
        leave.setUserName(leaveDto.getUserName());
        return leave;
    }
    public LeaveDto convertToDto(Leave leave){
        LeaveDto leaveDto = new LeaveDto();
        leaveDto.setLeaveDuration(leave.getLeaveDuration());
        leaveDto.setLeaveType(leave.getLeaveType());
        leaveDto.setDate(leave.getDate());
        leaveDto.setUserName(leave.getUserName());
        return leaveDto;
    }
}
