package com.example.FactoryShiftAttendanceSystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.FactoryShiftAttendanceSystem.model.Employee;
import com.example.FactoryShiftAttendanceSystem.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public Employee add(@RequestBody Employee e) {
        return repo.save(e);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee e) {
        Employee db = repo.findById(id).orElseThrow();
        db.setName(e.getName());
        db.setEmail(e.getEmail());
        db.setDepartment(e.getDepartment());
        return repo.save(db);
    }

    @GetMapping
    public List<Employee> all() {
        return repo.findAll();
    }
    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee e) {
        e.setPassword(passwordEncoder.encode(e.getPassword())); // hash password
        return ResponseEntity.ok(repo.save(e));
    }

    // Employee login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> req) {
        Employee emp = repo.findByEmail(req.get("email"));
        if(emp != null && passwordEncoder.matches(req.get("password"), emp.getPassword())) {
            return ResponseEntity.ok("Login successful");
            // Later you can return JWT token here
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }
}
