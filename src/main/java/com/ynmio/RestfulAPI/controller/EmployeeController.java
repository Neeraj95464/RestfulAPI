package com.ynmio.RestfulAPI.controller;

import com.ynmio.RestfulAPI.model.Employee;
import com.ynmio.RestfulAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/home/employees")
public class EmployeeController {

    EmployeeService service;

    public EmployeeController(@Qualifier("employeeServiceImpl") EmployeeService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Employee> saveSingle(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(service.saveSingle(employee), HttpStatus.CREATED);
    }
    @PostMapping("/add-all")
    public ResponseEntity<List<Employee>> saveEmployees(@RequestBody List<Employee> employees){
        return new ResponseEntity<List<Employee>>( service.saveEmployees(employees), HttpStatus.CREATED);
    }
    @GetMapping("/get-single/{id}")
    public ResponseEntity<Employee> getSingle(@PathVariable("id") Integer id) {
        Optional<Employee> employeeOptional = service.getSingle(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return new ResponseEntity<>(employee, HttpStatus.OK); // 200 OK if employee found
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if employee not found
        }
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAll(){
        Optional<List<Employee>> employees= service.getAll();
        if(employees.isPresent()){
            List<Employee> employee = employees.get();
            return new ResponseEntity<>(employee,HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Employee> updateData(@PathVariable Integer id,@RequestBody Employee employee){
        return new ResponseEntity<>(service.updateData(employee,id),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteData(@PathVariable Integer id){
        if(service.deleteData(id)){
            return new ResponseEntity<>(id+" this id Data deleted successful",HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
