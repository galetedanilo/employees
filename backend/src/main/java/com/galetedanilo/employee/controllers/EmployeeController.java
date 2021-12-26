package com.galetedanilo.employee.controllers;

import com.galetedanilo.employee.requests.EmployeeRequest;
import com.galetedanilo.employee.responses.EmployeeFullDetailsResponse;
import com.galetedanilo.employee.responses.EmployeeResponse;
import com.galetedanilo.employee.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> findAllEmployees(Pageable pageable) {
        Page<EmployeeResponse> employeeResponsePage = employeeService.findAllEmployees(pageable);

        return new ResponseEntity<>(employeeResponsePage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeByPrimaryKey(@PathVariable Long id) {
        EmployeeResponse employeeResponse = employeeService.findEmployeeByPrimaryKey(id);

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/details/{id}")
    public ResponseEntity<EmployeeFullDetailsResponse> findEmployeeByPrimaryKeyFullDetails(@PathVariable Long id) {
        EmployeeFullDetailsResponse employeeFullDetailsResponse = employeeService.findEmployeeByPrimaryKeyFullDetails(id);

        return new ResponseEntity<>(employeeFullDetailsResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> saveNewEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.saveNewEmployee(employeeRequest);

        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEmployeeByPrimaryKey(@PathVariable Long id) {
        employeeService.deleteEmployeeByPrimaryKey(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
