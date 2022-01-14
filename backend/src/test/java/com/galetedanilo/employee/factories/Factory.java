package com.galetedanilo.employee.factories;

import com.galetedanilo.employee.entities.Department;

import java.time.Instant;

public class Factory {

    public static Department departmentFactory() {
        Department department = new Department();

        department.builder()
                .name("Human Resources")
                .description("Human resources is the set of people who make up the workforce of an organization, ...")
                .createdAt(Instant.now())
                .build();

        return  department;
    }
}
