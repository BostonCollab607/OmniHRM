package com.example.performance_management.controller.timesheet;


import com.example.performance_management.utils.HelperUtil;
import com.example.performance_management.dto.timesheet.TaskEntryDto;
import com.example.performance_management.dto.timesheet.TaskFetchUserDateRange;
import com.example.performance_management.entity.timesheet.Task;
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
    private final HelperUtil helperUtil;
    private final TimesheetUtils timesheetUtils = new TimesheetUtils();

    public TimesheetController(TimesheetService timesheetService, HelperUtil helperUtil) {
        this.timesheetService = timesheetService;
        this.helperUtil = helperUtil;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTaskForUser(@RequestBody TaskEntryDto taskEntryDto) {
        taskEntryDto.setUsername(helperUtil.getLoggedInUser());
        timesheetService.addTaskForUser(taskEntryDto);
        return ResponseEntity.ok("Task created");
    }

    @GetMapping("/getall")
    public ResponseEntity<String> getAllTasks() {
        List<Task> tasks = timesheetService.getAllTasks();
        return ResponseEntity.ok("ok");
    }

    public void calculateOvertimeForUser() { // calculate if above x hours.
        int shiftHoursPerDay = TimesheetUtils.SHIFT_HOURS_PER_DAY;
        //
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTaskForUser(@PathVariable Long taskId) {
        timesheetService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted.");
    }

    public ResponseEntity<String> updateTaskForUser(@RequestBody TaskEntryDto taskEntryDto) {
        timesheetService.updateTaskForUser(taskEntryDto);
        return ResponseEntity.ok("Task updated.");
    }

    @GetMapping("/user/date/{username}/{dateStr}")
    public ResponseEntity<List<TaskEntryDto>> getTaskForUserAndCreatedDate(@PathVariable String username, @PathVariable String dateStr) {
        List<TaskEntryDto> tasks = timesheetService.getAllTaskForUserForDate(username, dateStr);
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/user/range")
    public ResponseEntity<List<TaskEntryDto>> getTasksForUserBetweenDates(@RequestBody TaskFetchUserDateRange taskFetchUserDateRange) {
        List<TaskEntryDto> tasks = timesheetService.getAllTaskForUserForDate(helperUtil.getLoggedInUser(), taskFetchUserDateRange.getStartDate(), taskFetchUserDateRange.getEndDate());
        return ResponseEntity.ok(tasks);
    }

    //the mapping is post - because we want to pass in some request body.
    @PostMapping("/user/range/hours")
    public ResponseEntity<String> getHoursWorkedUserBetweenDates(@RequestBody TaskFetchUserDateRange taskFetchUserDateRange) {

        List<TaskEntryDto> tasks = timesheetService.getAllTaskForUserForDate(helperUtil.getLoggedInUser(), taskFetchUserDateRange.getStartDate(), taskFetchUserDateRange.getEndDate());
        int minutes = 0;
        for (TaskEntryDto taskEntryDto : tasks) {
            String durationLogged = taskEntryDto.getDurationLogged();
            minutes += timesheetUtils.convertToMinutes(durationLogged);
        }
        minutes /= HelperUtil.MINUTES_PER_HOUR;
        return ResponseEntity.ok(String.valueOf(minutes));
    }

}
