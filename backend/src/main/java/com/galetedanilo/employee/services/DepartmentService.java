package com.galetedanilo.employee.services;

import com.galetedanilo.employee.entities.Department;
import com.galetedanilo.employee.repositories.DepartmentRepository;
import com.galetedanilo.employee.requests.DepartmentRequest;
import com.galetedanilo.employee.responses.DepartmentFullDetailsResponse;
import com.galetedanilo.employee.responses.DepartmentResponse;
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
public class DepartmentService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<DepartmentResponse> findAllDepartments(Pageable pageable) {
        Page<Department> departmentPage = departmentRepository.findAll(pageable);

        return departmentPage.map(department -> new DepartmentResponse(department));
    }

    @Transactional(readOnly = true)
    public DepartmentResponse findDepartmentByPrimaryKey(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        Department department = departmentOptional.orElseThrow(() -> new ResourceNotFoundException("Department by id " + id + " does not exist"));

        return new DepartmentResponse(department);
    }

    @Transactional(readOnly = true)
    public DepartmentFullDetailsResponse findDepartmentByPrimaryKeyFullDetails(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);

        Department department = departmentOptional.orElseThrow(() -> new ResourceNotFoundException("Department by id " + id + " does not exist"));

        return new DepartmentFullDetailsResponse(department);
    }

    @Transactional
    public DepartmentResponse saveNewDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();

        helperVerifyDepartmentNameExist(departmentRequest.getName());

        mapperDepartmentRequestToDepartment(department, departmentRequest);

        department.setCreatedAt(Instant.now());

        department = departmentRepository.save(department);

        return new DepartmentResponse(department);
    }

    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
        try {
            Department department = departmentRepository.getById(id);

            mapperDepartmentRequestToDepartment(department, departmentRequest);

            department = departmentRepository.save(department);

            return new DepartmentResponse(department);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Department by id " + id + " does not exist");
        }
    }

    public void deleteDepartmentByPrimaryKey(Long id) {
        try {
            departmentRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Integrity violation");
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Department by id " + id + " not found");
        }
    }

    private void mapperDepartmentRequestToDepartment(Department department, DepartmentRequest departmentRequest) {
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());
    }

    private void helperVerifyDepartmentNameExist(String departmentName) {
        if(departmentRepository.findByName(departmentName).isPresent()) {
            throw new DatabaseException("Department with name " + departmentName + " is already exist");
        }
    }
}
