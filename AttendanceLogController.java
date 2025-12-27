package com.example.FactoryShiftAttendanceSystem.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.FactoryShiftAttendanceSystem.model.AttendanceLogStatus;
import com.example.FactoryShiftAttendanceSystem.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FactoryShiftAttendanceSystem.model.AttendanceLog;
import com.example.FactoryShiftAttendanceSystem.repository.AttendanceLogRepository;
import com.example.FactoryShiftAttendanceSystem.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/attendancelogs")
public class AttendanceLogController {

    @Autowired
    private AttendanceLogRepository repo;
    @Autowired
    private EmployeeRepository empRepo;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Map<String, String> req) {

        Employee emp = empRepo.findByEmpCode(req.get("employeeCode"));
        if (emp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Employee not found");
        }
        AttendanceLog log = new AttendanceLog();
        log.setDate(LocalDate.parse(req.get("date")));
        log.setcheckIntime(LocalTime.parse(req.get("checkInTime")));
        log.setEmployee(emp);
        log.setshiftType(req.get("shiftType"));
        log.setAttendanceLog(
                AttendanceLogStatus.valueOf(req.get("status"))
        );

        repo.save(log);
        return ResponseEntity.status(HttpStatus.CREATED).body(log);
    }



    @PutMapping("/{id}")
    public AttendanceLog update(@PathVariable Long id,
                                @RequestBody Map<String,String> req) {

        AttendanceLog log = repo.findById(id).orElseThrow();
        log.setcheckOutTime(LocalTime.parse(req.get("checkOutTime")));
        return repo.save(log);
    }

    @GetMapping
    public List<AttendanceLog> all() {
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public AttendanceLog getById(@PathVariable Long id){
        Optional<AttendanceLog> data=repo.findById(id);
        if(data.isEmpty()){
            return  null;
        }
        return data.get();

    }
}
