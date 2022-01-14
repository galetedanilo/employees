package com.galetedanilo.employee.repositories;

import com.galetedanilo.employee.entities.Department;
import com.galetedanilo.employee.factories.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class DepartmentRepositoryTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void findDepartmentByPrimaryKeyShouldReturnOptionalWithDepartmentWhenIdExists() {
        Department department = Factory.departmentFactory();
        departmentRepository.save(department);

        Optional<Department> optionalDepartment = departmentRepository.findById(1L);

        Assertions.assertTrue(optionalDepartment.isPresent());
    }

    @Test
    public void findDepartmentByPrimaryKeyShouldReturnEmptyOptionalWhenIdDoesNotExisting() {
        Optional<Department> optionalDepartment = departmentRepository.findById(1L);

        Assertions.assertTrue(optionalDepartment.isEmpty());
    }

}
