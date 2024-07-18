package com.example.performance_management.mapper;

import com.example.performance_management.dto.performance.GoalDto;
import com.example.performance_management.entity.performance.Goal;

public class GoalMapper {


    public GoalMapper() {
    }

    public GoalDto convertToDto(Goal goal) {
        GoalDto goalDto = new GoalDto();
        goalDto.setId(goal.getId());
        goalDto.setTitle(goal.getTitle());
        goalDto.setManagerName(goal.getManagerName());
        goalDto.setEmployeeName(goal.getEmployeeName());
        goalDto.setDescription(goal.getDescription());
        goalDto.setCategory(goal.getCategory());
        goalDto.setStartDate(goal.getStartDate());
        goalDto.setEndDate(goal.getEndDate());
        goalDto.setKpis(goal.getKpis());
        goalDto.setMilestones(goal.getMilestones());
        goalDto.setFeedbackNotes(goal.getFeedbackNotes());
        goalDto.setSelfAssessment(goal.getSelfAssessment());
        goalDto.setCompleted(goal.isCompleted());
        return goalDto;
    }

    public Goal convertToGoal(GoalDto goalDto) {
        Goal goal = new Goal();
        goal.setTitle(goalDto.getTitle());
        goal.setManagerName(goalDto.getManagerName());
        goal.setEmployeeName(goalDto.getEmployeeName());
        goal.setDescription(goalDto.getDescription());
        goal.setCategory(goalDto.getCategory());
        goal.setStartDate(goalDto.getStartDate());
        goal.setEndDate(goalDto.getEndDate());
        goal.setKpis(goalDto.getKpis());
        goal.setMilestones(goalDto.getMilestones());
        goal.setFeedbackNotes(goalDto.getFeedbackNotes());
        goal.setSelfAssessment(goalDto.getSelfAssessment());
        goal.setCompleted(goalDto.isCompleted());
        return goal;
    }

}
