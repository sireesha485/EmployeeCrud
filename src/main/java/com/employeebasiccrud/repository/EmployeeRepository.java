package com.employeebasiccrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeebasiccrud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public List<Employee> findByEmployeeNameContaining(String name);

    public Optional<Employee> findByAadharEquals(Integer aadhar);
}
