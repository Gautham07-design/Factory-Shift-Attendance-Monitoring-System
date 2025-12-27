package com.example.FactoryShiftAttendanceSystem.model;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.CascadeType;
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

@Entity
public class AttendanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceid;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_code",referencedColumnName = "emp_code",nullable = false)
    private Employee employee;

    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String shiftType;

    @Enumerated(EnumType.STRING)
    private AttendanceLogStatus status;

    @OneToOne(mappedBy="attendanceLog",cascade=CascadeType.ALL)
    private AttendanceRecord attendanceRecord;

    @OneToOne(mappedBy="attendanceLog",cascade=CascadeType.ALL)
    private DailyAttendanceSummary dailyAttendanceSummaries;

    public AttendanceLog(){

    }
    public void setattendanceid(Long attendanceid){
        this.attendanceid=attendanceid;
    }
    public Long getattendanceid(){
        return attendanceid;
    }
    public void setEmployee(Employee employee){
        this.employee=employee;
    }
    public Employee getEmployee(){
        return employee;
    }
    public void setDate(LocalDate date){
        this.date=date;
    }
    public LocalDate getDate(){
        return date;

    }
    public void setcheckIntime(LocalTime checkInTime){
        this.checkInTime=checkInTime;
    }
    public LocalTime getcheckInTime(){
        return checkInTime;
    }
    public void setcheckOutTime(LocalTime checkOutTime){
        this.checkOutTime=checkOutTime;
    }
    public LocalTime getcheckOutTime(){
        return checkOutTime;
    }
    public void setshiftType(String shiftType){
        this.shiftType=shiftType;
    }
    public String getshiftType(){
        return shiftType;
    }
    public void setAttendanceLog(AttendanceLogStatus status){
        this.status=status;
    }
    public AttendanceLogStatus getAttendanceLogStatus(AttendanceLogStatus status){
        return status;
    }
    public void setAttendanceRecord(AttendanceRecord attendanceRecord){
        this.attendanceRecord=attendanceRecord;
    }
    public AttendanceRecord getAttendanceRecord(){
        return attendanceRecord;
    }
    public void setDailyAttendanceSummary(DailyAttendanceSummary dailyAttendanceSummaries){
        this.dailyAttendanceSummaries=dailyAttendanceSummaries;
    }





}
