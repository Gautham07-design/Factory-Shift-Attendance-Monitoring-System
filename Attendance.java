package com.example.FactoryShiftAttendanceSystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long attendanceid;
    @ManyToOne
    @JoinColumn(name="employee_code",referencedColumnName = "emp_code",nullable=false)
    private Employee employee;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }
    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    private LocalDate date;

    public Attendance(){

    }
    public void setId(Long attendanceid){
        this.attendanceid=attendanceid;
    }
    public Long getattendance(){
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
    public LocalDate getLocaldate(){
        return date;
    }
    public void setcheckIntime(LocalTime checkInTime){
        this.checkInTime=checkInTime;
    }
    public LocalTime getCheckInTime(){
        return checkInTime;
    }
    public void setcheckOutTime(LocalTime checkOuTime){
        this.checkOutTime=checkOuTime;
    }



}
