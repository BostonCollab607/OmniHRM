package com.example.performance_management.controller.performance;

import com.example.performance_management.dto.GoalDto;
import com.example.performance_management.service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Employee access entity.
 */
@RestController
@RequestMapping("/api/goals")
@CrossOrigin("*")
public class GoalController {

    private final GoalService goalService;
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public ResponseEntity<String> createGoal(@RequestBody GoalDto goalDto) {
        goalService.createGoal(goalDto);
        return ResponseEntity.ok("Goal created");
    }

    @GetMapping
    public ResponseEntity<List<GoalDto>> getAllGoals() {
        return ResponseEntity.ok(goalService.getAllGoals());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<GoalDto>> getAllGoalsByUser(@PathVariable String username) {
        return ResponseEntity.ok(goalService.getAllGoals(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalDto> getGoalById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.getGoalById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGoal(@PathVariable Long id, @RequestBody GoalDto goalDto) {
        goalService.updateGoal(id, goalDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

}