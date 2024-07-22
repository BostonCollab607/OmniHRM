package com.example.performance_management.controller.timesheet;


import com.example.performance_management.dto.timesheet.TaskEntryDto;
import com.example.performance_management.dto.timesheet.TaskFetchUserDate;
import com.example.performance_management.dto.timesheet.TaskFetchUserDateRange;
import com.example.performance_management.service.timesheet.TimesheetService;
import com.example.performance_management.utils.TimesheetUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timesheet")
@CrossOrigin("*")
public class TimesheetController {

    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTaskForUser(@RequestBody TaskEntryDto taskEntryDto){ // task description, user, time logged, start time, end time, date & logged at (now)
        timesheetService.addTaskForUser(taskEntryDto);
        return ResponseEntity.ok("Task created");
    }

    public void calculateOvertimeForUser(){ // calculate if above x hours.
        int shiftHoursPerDay = TimesheetUtils.SHIFT_HOURS_PER_DAY;
        //
    }

    public ResponseEntity<String> deleteTaskForUser(@RequestBody Long taskId){ //task id.
        timesheetService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted.");
    }

    public ResponseEntity<String> updateTaskForUser(@RequestBody TaskEntryDto taskEntryDto){
        timesheetService.updateTaskForUser(taskEntryDto);
        return ResponseEntity.ok("Task updated.");
    }

    @GetMapping("/user/date")
    public ResponseEntity<List<TaskEntryDto>> getTaskForUserAndCreatedDate(@RequestBody TaskFetchUserDate taskFetchUserDate){
        List<TaskEntryDto> tasks = timesheetService.getAllTaskForUserForDate(taskFetchUserDate.getUsername(), taskFetchUserDate.getCreatedDate());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/user/range")
    public ResponseEntity<List<TaskEntryDto>> getTasksForUserBetweenDates(@RequestBody TaskFetchUserDateRange taskFetchUserDateRange){
        List<TaskEntryDto> tasks = timesheetService.getAllTaskForUserForDate(taskFetchUserDateRange.getUsername(), taskFetchUserDateRange.getStartDate(), taskFetchUserDateRange.getEndDate());
        return ResponseEntity.ok(tasks);
    }


}