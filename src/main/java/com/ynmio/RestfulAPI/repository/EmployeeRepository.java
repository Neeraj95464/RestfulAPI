package com.ynmio.RestfulAPI.repository;

import com.ynmio.RestfulAPI.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
