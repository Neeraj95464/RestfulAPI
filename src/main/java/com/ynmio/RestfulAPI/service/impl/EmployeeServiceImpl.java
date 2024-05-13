package com.ynmio.RestfulAPI.service.impl;
import com.ynmio.RestfulAPI.model.Employee;
import com.ynmio.RestfulAPI.repository.EmployeeRepository;
import com.ynmio.RestfulAPI.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee saveSingle(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }

    @Override
    public Optional<Employee> getSingle(Integer id) {
        return repository.findById(id);
}

    @Override
    public Optional<List<Employee>> getAll() {
        List<Employee> employees= repository.findAll();
        if (!employees.isEmpty()) {
            return Optional.of(employees); // Wrap the list in an Optional
        } else {
            return Optional.empty(); // Return Optional.empty() if list is empty
        }
    }

    @Override
    public Employee updateData(Employee employee,Integer id) {
        Employee DataBaseEmp=repository.findById(id).orElseThrow(()->new RuntimeException("Data not found"));
        DataBaseEmp.setFirstName(employee.getFirstName());
        DataBaseEmp.setLastName(employee.getLastName());
        DataBaseEmp.setSalary(employee.getSalary());
        repository.save(DataBaseEmp);
        return DataBaseEmp;
    }

    @Override
    public boolean deleteData(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

