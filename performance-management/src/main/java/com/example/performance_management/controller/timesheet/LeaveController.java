package com.example.performance_management.controller.timesheet;

import com.example.performance_management.dto.timesheet.LeaveDto;
import com.example.performance_management.service.timesheet.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/LeaveService")
@CrossOrigin("*")
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/applyLeave")
    public void applyLeave(@RequestBody LeaveDto leaveDto){
        leaveService.SubmitLeave(leaveDto);
    }

    @PostMapping("/applyLeaves")
    public void applyLeaves(@RequestBody List<LeaveDto> leaveDtoList){
        for(LeaveDto leaveDto:leaveDtoList){
            leaveService.SubmitLeave(leaveDto);
        }
    }

    @PostMapping("/getLeaveBalance")
    public HashMap<String , Float> getLeaveBalance(@RequestBody LeaveDto leaveDto){
        String username = leaveDto.getUserName();
        return leaveService.getRemainingLeavesPerType(username);
    }

    @PostMapping("/getLeaveDatesPerType")
    public HashMap<String , List<String>> getLeaveDates(@RequestBody LeaveDto leaveDto){
        String username = leaveDto.getUserName();
        return leaveService.getLeaveDatesPerType(username);
    }

}
