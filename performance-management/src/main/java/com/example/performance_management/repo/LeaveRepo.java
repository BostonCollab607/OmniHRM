package com.example.performance_management.repo;

import com.example.performance_management.entity.timesheet.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface LeaveRepo extends MongoRepository<Leave,Long> {

    Collection<Leave> findByUserName(String username);
}
