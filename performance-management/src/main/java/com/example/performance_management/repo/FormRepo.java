package com.example.performance_management.repo;

import com.example.performance_management.entity.performance.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepo extends MongoRepository<Form, Long> {

    Optional<Form> findByFormNameAndCompanyName(String formName, String companyName);
    Optional<Form> findByFormName(String name);
    Optional<List<Form>> findByCompanyName(String companyName);
}
