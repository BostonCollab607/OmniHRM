package com.example.performance_management.repo;

import com.example.performance_management.entity.performance.EmployeeForms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeFormRepo extends MongoRepository<EmployeeForms, Long> {

    Optional<EmployeeForms> findByCompanyNameAndEmployeeNameAndFormName(String companyName, String employeeName, String formName);

    Optional<EmployeeForms> findByCompanyNameAndEmployeeName(String companyName, String employeeName);

}
