package com.example.FactoryShiftAttendanceSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FactoryShiftAttendanceSystem.model.AttendanceLog;
import com.example.FactoryShiftAttendanceSystem.repository.AttendanceLogRepository;

@Service
public class AttendanceLogService {
    @Autowired
    AttendanceLogRepository attendanceLogRepository;
    public List<AttendanceLog> getAll() {
        List<AttendanceLog> list=attendanceLogRepository.findAll();
        return list;
    }
    public AttendanceLog update(Long id, AttendanceLog newAttendancelog) {
        Optional<AttendanceLog> data=attendanceLogRepository.findById(id);
        if(data.isEmpty()){
            return null;
        }
        AttendanceLog attendanceLog=data.get();
        attendanceLog.setattendanceid(newAttendancelog.getattendanceid());
        attendanceLog.setEmployee(attendanceLog.getEmployee());
        attendanceLog.setDate(newAttendancelog.getDate());
        attendanceLog.setcheckIntime(newAttendancelog.getcheckInTime());
        attendanceLog.setcheckOutTime(newAttendancelog.getcheckOutTime());
        attendanceLog.setshiftType(newAttendancelog.getshiftType());

        return attendanceLogRepository.save(attendanceLog);
    }

}
