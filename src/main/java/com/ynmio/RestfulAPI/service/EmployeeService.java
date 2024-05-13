package com.ynmio.RestfulAPI.service;

import com.ynmio.RestfulAPI.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveSingle(Employee employee);
    List<Employee> saveEmployees(List<Employee> employees);
    Optional<Employee> getSingle(Integer id);
    Optional<List<Employee>> getAll();
    Employee updateData(Employee employee, Integer id);
    boolean deleteData(Integer id);
}
