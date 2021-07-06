package com.devsuperior.employees.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.employees.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByName(String name);
}
