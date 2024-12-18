package com.example.performance_management.entity;

import com.example.performance_management.entity.role.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "employee_company_details")
@Data
@NoArgsConstructor
@Getter
@Setter
public class EmployeeCompanyDetails {

    // initial password.
    private String password;
    private List<Role> roles;

    //job details.
    public String team;
    public String managerEmail;
    @Id
    public Long employeeId;
    public String businessTitle;
    public String jobProfile;
    public String location;
    public String hireDate;
    public String lengthOfService;
    public String telephone;

    public String email; // can use this... to map the employee in hrm system.
    public String userName;
    public String fullName;
    public String workAddress;

    public String companyName;

    //personal details.

    public String gender;
    public String dateOfBirth;
    public String age;
    public String countryOfBirth;
    public String cityOfBirth;
    public String maritalStatus;
    public String nationality;
    public String dependantName;
    public String relationToDependant;

    // salary details.
    public int baseSalary;
    public int bonusAllotted;
    public int stocksOffered;
    public int totalComp;


}
