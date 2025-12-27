package com.example.FactoryShiftAttendanceSystem.controller;



import java.time.LocalDate;
import java.util.Map;

import com.example.FactoryShiftAttendanceSystem.model.*;
import com.example.FactoryShiftAttendanceSystem.repository.AttendanceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.FactoryShiftAttendanceSystem.repository.DailyAttendanceSummaryRepository;
import com.example.FactoryShiftAttendanceSystem.repository.EmployeeRepository;
import com.example.FactoryShiftAttendanceSystem.service.DailyAttendanceSummaryService;

@RestController
@RequestMapping("/api/daily-summary")
public class DailyAttendanceSummaryController {

    @Autowired
    DailyAttendanceSummaryService daily_attendance_summary;

    @Autowired
    DailyAttendanceSummaryRepository dailyattendancesummaryrepository;
    @Autowired
    AttendanceLogRepository attendanceLogRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/api/attendance")
    public ResponseEntity<Attendance> createAttendance(Attendance attendance){
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/employee/{id}")
    public Page<DailyAttendanceSummary> returnByValue(@PathVariable Long id,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return daily_attendance_summary.getDailyAttendanceSummary(id, pageable);
    }

    @GetMapping("/employee/code/{empcode}")
    public ResponseEntity<Page<DailyAttendanceSummary>> getSummary(
            @PathVariable String empcode,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("localDate").descending());

        Page<DailyAttendanceSummary> pageresult = dailyattendancesummaryrepository
                .findByEmpCodeAndDateRange(
                        empcode,
                        LocalDate.parse(startDate),
                        LocalDate.parse(endDate),
                        pageable
                );

        return ResponseEntity.ok(pageresult);
    }

    @PostMapping("/create")
    public ResponseEntity<DailyAttendanceSummary> createSummary(
            @RequestBody Map<String, String> req) {

        Employee employee = employeeRepository.findByEmpCode(req.get("employeeCode"));

        DailyAttendanceSummary summary = new DailyAttendanceSummary();
        summary.setEmployee(employee);
        summary.setLocalDate(LocalDate.parse(req.get("date")));

        return ResponseEntity.ok(
                dailyattendancesummaryrepository.save(summary)
        );
    }

    @GetMapping("/employee/code/{empcode}/filter")
    public ResponseEntity<Page<DailyAttendanceSummary>> getFilteredSummary(
            @PathVariable String empcode,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("localDate").descending());

        Page<DailyAttendanceSummary> pageresult = dailyattendancesummaryrepository
                .findByEmpCodeAndDateRange(
                        empcode,
                        LocalDate.parse(startDate),
                        LocalDate.parse(endDate),
                        pageable
                );

        return ResponseEntity.ok(pageresult);
    }


    @GetMapping
    public ResponseEntity<Page<DailyAttendanceSummary>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("localDate").descending());
        Page<DailyAttendanceSummary> allSummaries = dailyattendancesummaryrepository.findAll(pageable);
        return ResponseEntity.ok(allSummaries);
    }

    // 2️⃣ GET summary by ID
    @GetMapping("/{id}")
    public ResponseEntity<DailyAttendanceSummary> getById(@PathVariable Long id) {
        return dailyattendancesummaryrepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyAttendanceSummary> updateSummary(
            @PathVariable Long id,
            @RequestBody Map<String, String> req
    ) {
        DailyAttendanceSummary summary = dailyattendancesummaryrepository.findById(id)
                .orElse(null);
        if (summary == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields if present
        if (req.containsKey("date")) {
            summary.setLocalDate(LocalDate.parse(req.get("date")));
        }
        if (req.containsKey("status")) {
            summary.setAttendancelogstatus(
                    Enum.valueOf(AttendanceLogStatus.class, req.get("status"))
            );
        }
        if (req.containsKey("employeeCode")) {
            Employee emp = employeeRepository.findByEmpCode(req.get("employeeCode"));
            summary.setEmployee(emp);
        }

        return ResponseEntity.ok(dailyattendancesummaryrepository.save(summary));
    }
    @PostMapping
    public ResponseEntity<DailyAttendanceSummary> createSummaryAttendance(
            @RequestBody Map<String, String> req) {

        Employee employee = employeeRepository.findByEmpCode(req.get("employeeCode"));

        // Find the attendance log for the same employee and date
        AttendanceLog log = attendanceLogRepository
                .findByEmployeeAndDate(employee, LocalDate.parse(req.get("date")));

        DailyAttendanceSummary summary = new DailyAttendanceSummary();
        summary.setEmployee(employee);
        summary.setLocalDate(LocalDate.parse(req.get("date")));
        summary.setAttendanceLog(log); // <-- link attendance log

        return ResponseEntity.ok(
                dailyattendancesummaryrepository.save(summary)
        );
    }

}
