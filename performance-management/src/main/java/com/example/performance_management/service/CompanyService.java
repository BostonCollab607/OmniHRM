package com.example.performance_management.service;

import com.example.performance_management.dto.CompanyDto;
import com.example.performance_management.dto.EmployeeCompanyDetailsDto;
import com.example.performance_management.entity.Company;
import com.example.performance_management.entity.role.Role;
import com.example.performance_management.entity.role.RoleEnum;
import com.example.performance_management.exception.CustomException;
import com.example.performance_management.mapper.CompanyMapper;
import com.example.performance_management.mongoidgen.CompanySequenceGeneratorService;
import com.example.performance_management.repo.CompanyRepo;
import com.example.performance_management.utils.HelperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final CompanyMapper companyMapper;
    private final CompanySequenceGeneratorService companySequenceGeneratorService;
    private final RoleService roleService;
    private final AuthService authService;
    private final EmployeeCompanyDetailsService employeeCompanyDetailsService;
    private final HelperUtil helperUtil;

    public CompanyService(CompanyRepo companyRepo, CompanyMapper companyMapper, CompanySequenceGeneratorService companySequenceGeneratorService, RoleService roleService, AuthService authService, EmployeeCompanyDetailsService employeeCompanyDetailsService, HelperUtil helperUtil) {
        this.companyRepo = companyRepo;
        this.companyMapper = companyMapper;
        this.companySequenceGeneratorService = companySequenceGeneratorService;
        this.roleService = roleService;
        this.authService = authService;
        this.employeeCompanyDetailsService = employeeCompanyDetailsService;
        this.helperUtil = helperUtil;
    }

    public void createCompany(String companyName, String companyDomain, String companyEmail) {
        checkIfExists(companyName);
        long id = companySequenceGeneratorService.getCompanySequenceNumber(Company.ID_KEY, Company.ID_VAL, Company.GENERATED_ID);
        Company company = new Company(id, companyName, companyDomain, companyEmail);
        companyRepo.save(company);
    }

    public void createCompanyAdmin(String userName, String companyEmail, String userPassword, String companyName) {

        Role officeAdmin = roleService.getRole(RoleEnum.COMPANYADMIN);
        EmployeeCompanyDetailsDto employeeCompanyDetailsDto = new EmployeeCompanyDetailsDto();

        employeeCompanyDetailsDto.setCompanyName(companyName);
        employeeCompanyDetailsDto.setEmail(companyEmail);
        employeeCompanyDetailsDto.setUserName(userName);
        employeeCompanyDetailsDto.setPassword(userPassword);
        ArrayList<Role> roles = new ArrayList<>(); roles.add(officeAdmin);
        employeeCompanyDetailsDto.setRoles(roles);
        employeeCompanyDetailsService.createEmployeeForCompany(employeeCompanyDetailsDto);
    }

    public CompanyDto updateCompany(CompanyDto companyDto) {
        checkIfExists(companyDto.getCompanyName());
        Company company = companyMapper.convertToEntity(companyDto);
        companyRepo.save(company);
        return companyDto;
    }
    private void checkIfExists(String companyName) {
        if (checkForSameName(companyName)) {
            throw new CustomException("Company already registered.");
        }
    }
    public boolean checkForSameName(String name) {
        return companyRepo.findByCompanyNameStartsWith(name).isPresent();
    }
    public Company getCompanyById(Long id) {
        Optional<Company> optionalCom = companyRepo.findById(id);
        if (optionalCom.isEmpty()) {
            throw new CustomException("Company does not exist");
        }
        return optionalCom.get();
    }

    public Company getCompanyByName(String companyName) {
        Optional<Company> optionalCompany = companyRepo.findByCompanyNameStartsWith(companyName);
        if (optionalCompany.isEmpty()) {
            throw new CustomException("Company is not present");
        }
        return optionalCompany.get();
    }

    private Company getCompany(Optional<Company> optionalCompany) {
        if (optionalCompany.isEmpty()) {
            throw new CustomException("Company does not exist");
        }
        return optionalCompany.get();
    }

    public String getAll() {
        List<Company> all = companyRepo.findAll();
        StringJoiner stringJoiner = new StringJoiner(", ", "Companies{", "}");
        all.stream().forEach(company -> stringJoiner.add(company.getCompanyName()));
        return stringJoiner.toString();
    }

    public void deleteCompanyById(Long id) {
        companyRepo.deleteById(id);
    }
    public void deleteCompanyByName(String companyName) {
        companyRepo.deleteByCompanyName(companyName);
    }

    public Future<String> sendMail(String companyEmail) {
        int index = companyEmail.indexOf('@');
        String password = companyEmail.substring(0, index);
        helperUtil.sendMail(companyEmail, password);
        return CompletableFuture.completedFuture(password);
    }
}
