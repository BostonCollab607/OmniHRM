package com.example.performance_management.entity.timesheet;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Leave_Table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Leave {
    public List<String> date;
    public Float leaveDuration;
    public String userName;
    @NonNull public String leaveType;
}
