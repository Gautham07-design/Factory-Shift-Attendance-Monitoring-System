package com.example.FactoryShiftAttendanceSystem.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FactoryShiftAttendanceSystem.model.AdminUser;
import com.example.FactoryShiftAttendanceSystem.repository.AdminUserRepository;
import com.example.FactoryShiftAttendanceSystem.service.AdminUserService;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private final AdminUserRepository adminUserRepository;
    private final AdminUserService adminUserService;
    public AdminUserController(AdminUserRepository adminUserRepository,AdminUserService adminUserService){
        this.adminUserRepository=adminUserRepository;
        this.adminUserService=adminUserService;
    }
    @GetMapping("/api/employees")
    public ResponseEntity<AdminUser> noemployeeexist(AdminUser adminUser){
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<AdminUser> getbyuniqueId(@PathVariable Long id){
        Optional<AdminUser> data=adminUserRepository.findById(id);
        if(data.isPresent()) {
            return ResponseEntity.ok(data.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<AdminUser>> getAllAdmins(){
        List<AdminUser> list=adminUserRepository.findAll();
        return ResponseEntity.ok(list);
    }
    @PostMapping("/create")
    public ResponseEntity<AdminUser> addingAdmin(@RequestBody AdminUser adminUser){
        AdminUser adminUser2=adminUserService.addAdmin(adminUser);
        return ResponseEntity.ok(adminUser2);

    }
    @GetMapping("/{id}")
    public AdminUser getById(@PathVariable Long id) {
        return adminUserRepository.findById(id).orElseThrow();
    }
    @PutMapping("/{id}")
    public ResponseEntity<AdminUser> updateAdmin(
            @PathVariable Long id,
            @RequestBody AdminUser adminUser) {

        AdminUser existing = adminUserRepository.findById(id).orElseThrow();

        existing.setUsername(adminUser.getUsername());
        existing.setPassword(adminUser.getPassword());

        return ResponseEntity.ok(adminUserRepository.save(existing));
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Admin registration
    @PostMapping("/register")
    public ResponseEntity<AdminUser> register(@RequestBody AdminUser admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return ResponseEntity.ok(adminUserRepository.save(admin));
    }

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> req) {
        AdminUser admin = adminUserRepository.findByEmail(req.get("email"));
        if(admin != null && passwordEncoder.matches(req.get("password"), admin.getPassword())) {
            return ResponseEntity.ok("Login successful");
            // Later you can return JWT token here
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }





}
