package com.example.performance_management.service.timesheet;

import com.example.performance_management.dto.timesheet.LeaveDto;
import com.example.performance_management.entity.timesheet.Leave;
import com.example.performance_management.mapper.LeaveMapper;
import com.example.performance_management.repo.LeaveRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


@Service
public class LeaveService {
    private final LeaveRepo leaveRepo;
    private final LeaveMapper leaveMapper = new LeaveMapper();
    private final HashMap<String,Float> leaveTypeLimits = new HashMap<>(){{
        put(LeaveDto.SICK_LEAVE,10.0F);
        put(LeaveDto.CASUAL_LEAVE,10.0F);
        put(LeaveDto.EARNED_LEAVE,20.0F);
    }};


    public LeaveService(LeaveRepo leaveRepo) {
        this.leaveRepo = leaveRepo;
    }


    public void SubmitLeave(LeaveDto leaveDto) {
        Leave leave = leaveMapper.convertToEntity(leaveDto);
        HashMap<String,Float> leaveMap = getRemainingLeavesPerType(leaveDto.getUserName());
        if(leaveMap.get(leaveDto.getLeaveType())> leaveDto.getLeaveDuration()*leaveDto.getDate().size()/8.0F) {
            leaveRepo.save(leave);
        }
    }

    public HashMap<String, Float> getRemainingLeavesPerType(String username) {
        Collection<Leave> leaveList = leaveRepo.findByUserName(username);
        HashMap<String,Float> leaveMap = new HashMap<>(leaveTypeLimits);
       for(Leave leave: leaveList){
           String leaveType = leave.getLeaveType();
           if(leaveType==null){
               leaveType= LeaveDto.EARNED_LEAVE;
           }
           Float finalValue = leaveMap.get(leaveType)-leave.getLeaveDuration()*leave.getDate().size()/8.0F;
           leaveMap.put(leaveType,finalValue);
       }
       return leaveMap;
    }

    public HashMap<String, List<String>> getLeaveDatesPerType(String username) {
        Collection<Leave> leaveList = leaveRepo.findByUserName(username);
        HashMap<String,List<String>> hashMap = new HashMap<>();

        for(String key : leaveTypeLimits.keySet()){
            hashMap.put(key,new ArrayList<>());
        }
        for(Leave leave: leaveList){
            LeaveDto leaveDto = leaveMapper.convertToDto(leave);
            hashMap.get(leaveDto.getLeaveType()).addAll(leaveDto.getDate());
        }

        return hashMap;
    }
}
