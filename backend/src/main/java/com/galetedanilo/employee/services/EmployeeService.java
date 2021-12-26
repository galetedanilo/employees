package com.galetedanilo.employee.services;

import com.galetedanilo.employee.entities.Department;
import com.galetedanilo.employee.entities.Employee;
import com.galetedanilo.employee.entities.enums.Genre;
import com.galetedanilo.employee.repositories.DepartmentRepository;
import com.galetedanilo.employee.repositories.EmployeeRepository;
import com.galetedanilo.employee.requests.EmployeeRequest;
import com.galetedanilo.employee.responses.EmployeeFullDetailsResponse;
import com.galetedanilo.employee.responses.EmployeeResponse;
import com.galetedanilo.employee.services.exceptions.DatabaseException;
import com.galetedanilo.employee.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findAllEmployees(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        return employeePage.map(employee -> new EmployeeResponse(employee));
    }

    @Transactional(readOnly = true)
    public EmployeeResponse findEmployeeByPrimaryKey(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        Employee employee = employeeOptional.orElseThrow(() -> new ResourceNotFoundException("Employee by id " + id + " does not exist"));

        return new EmployeeResponse(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeFullDetailsResponse findEmployeeByPrimaryKeyFullDetails(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        Employee employee = employeeOptional.orElseThrow(() -> new ResourceNotFoundException("Employee by id " + id + " does not exist"));

        return new EmployeeFullDetailsResponse(employee);
    }

    @Transactional
    public EmployeeResponse saveNewEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();

        helperVerifyEmployeeCpfExist(employeeRequest.getCpf());
        helperVerifyEmployeeEmailExist(employeeRequest.getEmail());

        mapperEmployeeRequestToEmployee(employee, employeeRequest);

        employee.setCreatedAt(Instant.now());

        employee = employeeRepository.save(employee);

        return new EmployeeResponse(employee);
    }

    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        try {
            Employee employee = employeeRepository.getById(id);

            mapperEmployeeRequestToEmployee(employee, employeeRequest);

            employee.setUpdatedAt(Instant.now());

            employee = employeeRepository.save(employee);

            return new EmployeeResponse(employee);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Employee by id " + id + " does not exist");
        }
    }

    public void deleteEmployeeByPrimaryKey(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Integrity violation");
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Employee by id " + id + " not found");
        }
    }

    private void mapperEmployeeRequestToEmployee(Employee employee, EmployeeRequest employeeRequest) {
        try {
            Optional<Department> departmentOptional = departmentRepository.findById(employeeRequest.getDepartmentId());

            Department department = departmentOptional.orElseThrow(() -> new ResourceNotFoundException("Department does not exist"));

            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setGenre(Genre.valueOf(employeeRequest.getGenre()));
            employee.setCpf(employeeRequest.getCpf());
            employee.setBirthDate(employeeRequest.getBirthDate());
            employee.setUsername(employeeRequest.getUsername());
            employee.setImageUri(employeeRequest.getImageUri());
            employee.setEmail(employeeRequest.getEmail());
            employee.setPassword(employeeRequest.getPassword());
            employee.setAmount(employeeRequest.getAmount());
            employee.setDepartment(department);
        } catch (IllegalArgumentException ex) {
            throw  new DatabaseException("Illegal argument to genre");
        }
    }

    private void helperVerifyEmployeeCpfExist(String employeeCpf) {
        if(employeeRepository.findByCpf(employeeCpf).isPresent())
            throw new DatabaseException("Employee with cpf " + employeeCpf + " is already exist");
    }

    private void helperVerifyEmployeeEmailExist(String employeeEmail) {
        if(employeeRepository.findByEmail(employeeEmail).isPresent())
            throw new DatabaseException("Employee with email " + employeeEmail + " is already exist");
    }
}
