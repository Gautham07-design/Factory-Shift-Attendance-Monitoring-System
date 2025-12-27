package com.example.FactoryShiftAttendanceSystem.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "emp_code"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeid;

    @Column(name = "emp_code", nullable = false)
    private String empCode;

    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;  // <-- added password field

    private String department;

    public Employee() {}

    public Long getId() {
        return employeeid;
    }

    public void setId(Long employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {  // <-- getter for password
        return password;
    }

    public void setPassword(String password) {  // <-- setter for password
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
