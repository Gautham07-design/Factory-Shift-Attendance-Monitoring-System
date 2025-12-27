package com.example.FactoryShiftAttendanceSystem.repository;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.FactoryShiftAttendanceSystem.model.DailyAttendanceSummary;

@Repository
public interface DailyAttendanceSummaryRepository extends JpaRepository<DailyAttendanceSummary,Long> {

    Page<DailyAttendanceSummary> findByEmployeeId(Long id, Pageable pageable);
    @Query("""
        SELECT d FROM DailyAttendanceSummary d
        WHERE d.employee.empCode = :empCode
        AND d.localDate BETWEEN :startDate AND :endDate
    """)
    Page<DailyAttendanceSummary> findByEmpCodeAndDateRange(
            @Param("empCode") String empCode,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );



}
