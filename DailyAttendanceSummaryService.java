package com.example.FactoryShiftAttendanceSystem.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.FactoryShiftAttendanceSystem.model.DailyAttendanceSummary;
import com.example.FactoryShiftAttendanceSystem.repository.DailyAttendanceSummaryRepository;

@Service

public class DailyAttendanceSummaryService {
    @Autowired
    DailyAttendanceSummaryRepository dailyattendancesummaryrepository;


    public Object getByid(Long id) {
        Optional<DailyAttendanceSummary> data=dailyattendancesummaryrepository.findById(id);
        if(data.isEmpty()){
            return null;
        }
        return data.get();

    }


    public Page<DailyAttendanceSummary> getDailyAttendanceSummary(Long id, Pageable pageable) {
        return dailyattendancesummaryrepository.findByEmployeeId(id, pageable);
    }


    public Page<DailyAttendanceSummary> getsumary(Pageable pageable) {
        return dailyattendancesummaryrepository.findAll(pageable);
    }
    public Page<DailyAttendanceSummary> getByEmpCodeAndDateRange(
            String empCode,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {

        return dailyattendancesummaryrepository
                .findByEmpCodeAndDateRange(empCode, startDate, endDate, pageable);
    }








}
