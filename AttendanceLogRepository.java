package com.example.FactoryShiftAttendanceSystem.repository;

import com.example.FactoryShiftAttendanceSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FactoryShiftAttendanceSystem.model.AttendanceLog;

import java.time.LocalDate;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog,Long> {

    AttendanceLog findByEmployeeAndDate(Employee employee, LocalDate date);
}
