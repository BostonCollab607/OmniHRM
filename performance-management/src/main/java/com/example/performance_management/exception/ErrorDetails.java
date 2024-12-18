package com.example.performance_management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime dateTime;
    private String message;
    private String details;

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "dateTime=" + dateTime +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
