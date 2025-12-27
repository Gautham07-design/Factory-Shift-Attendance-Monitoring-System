package com.example.FactoryShiftAttendanceSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.FactoryShiftAttendanceSystem.model.AdminUser;
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {

    AdminUser findByEmail(String email);
}
