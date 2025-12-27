package com.example.FactoryShiftAttendanceSystem.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
        name="attendance_records",
        uniqueConstraints={
                @UniqueConstraint(columnNames = {"employee_code","attendance_date"})
        }
)
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long attendanceRecordid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_code",referencedColumnName = "emp_code",nullable = false)
    private Employee employee;
    @Column(name = "attendance_date")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private AttendanceLogStatus status;

    @OneToOne
    @JoinColumn(name="attendance_log_id",nullable = false)
    private AttendanceLog attendanceLog;

    public AttendanceRecord(){

    }

    public void setId(Long id){
        this.attendanceRecordid=id;
    }
    public Long getId(){
        return attendanceRecordid;
    }
    public void setEmployee(Employee employee){
        this.employee=employee;
    }
    public Employee getEmployee(){
        return employee;
    }
    public LocalDate localDate(){
        return date;
    }
    public void setLocalDate(LocalDate date){
        this.date=date;
    }
    public AttendanceLogStatus getAttendanceLogStatus(){
        return status;
    }
    public void setAttendancelogstatus(AttendanceLogStatus status){
        this.status=status;

    }
    public AttendanceLog getAttendanceLog(){
        return attendanceLog;
    }
    public void setAttendanceLog(AttendanceLog attendanceLog){
        this.attendanceLog=attendanceLog;
    }






}

