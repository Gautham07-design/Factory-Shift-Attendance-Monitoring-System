package com.example.FactoryShiftAttendanceSystem.service;

import com.example.FactoryShiftAttendanceSystem.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.FactoryShiftAttendanceSystem.repository.AdminUserRepository;

@Service
public class AdminUserService {
    @Autowired
    AdminUserRepository adminUserRepository;
    public AdminUser addAdmin(AdminUser adminUser){
        return adminUserRepository.save(adminUser);
    }

}
