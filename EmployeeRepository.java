package com.example.FactoryShiftAttendanceSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FactoryShiftAttendanceSystem.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmpCode(String empCode);

    Employee findByEmail(String email);
}
