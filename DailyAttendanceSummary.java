package com.example.FactoryShiftAttendanceSystem.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name="daily_attendance_summary",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_code","summary_date"})
        }
)
public class DailyAttendanceSummary {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long summaryid;

    @ManyToOne
    @JoinColumn(name="employee_code",referencedColumnName = "emp_code",nullable=false)
    private Employee employee;
    @Column(name="summary_date",nullable=false)
    private LocalDate localDate;
    @Enumerated(EnumType.STRING)
    private AttendanceLogStatus status;
    @OneToOne
    @JoinColumn(name="attendance_log_id",nullable = true)
    private AttendanceLog attendanceLog;

    public DailyAttendanceSummary(){

    }

    public void setId(Long summaryid){
        this.summaryid=summaryid;
    }
    public Long getId(){
        return summaryid;
    }
    public void setEmployee(Employee employee){
        this.employee=employee;
    }
    public Employee getEmployee(){
        return employee;
    }
    public void setLocalDate(LocalDate localDate){
        this.localDate=localDate;
    }
    public LocalDate getLocalDate(){
        return localDate;
    }
    public void setAttendancelogstatus(AttendanceLogStatus status){
        this.status=status;
    }
    public AttendanceLogStatus getAttendanceLogStatus(){
        return status;
    }
    public void setAttendanceLog(AttendanceLog attendanceLog){
        this.attendanceLog=attendanceLog;

    }
    public AttendanceLog getAttendanceLog(){
        return attendanceLog;
    }
}
