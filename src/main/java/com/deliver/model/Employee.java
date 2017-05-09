package com.deliver.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 91574 on 2017/4/18.
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int mId;

    @Column(name="employee_id",unique = true,nullable = false)
    private String mEmployeeId;

    @Column(name = "name", nullable = false)
    private String mName;

    @Column(name="age",nullable = false)
    private int mAge;

    @Column(name="salary",nullable = false)
    private float mSalary;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Point mPoint;

    @Column(name="entering_time",nullable = false)
    private Timestamp mEnteringTime;


    @OneToMany(mappedBy = "mId")
    private List<Attendance> mAttendence;

    @ManyToMany
    private List<Position> mPosition;


    public Employee(){

    }

    public Employee(String mEmployeeId, String mName, int mAge, float mSalary, Point mPoint, Timestamp mEnteringTime) {
        this.mEmployeeId = mEmployeeId;
        this.mName = mName;
        this.mAge = mAge;
        this.mSalary = mSalary;
        this.mPoint = mPoint;
        this.mEnteringTime = mEnteringTime;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmEmployeeId() {
        return mEmployeeId;
    }

    public void setmEmployeeId(String mEmployeeId) {
        this.mEmployeeId = mEmployeeId;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    public float getmSalary() {
        return mSalary;
    }

    public void setmSalary(float mSalary) {
        this.mSalary = mSalary;
    }

    public Point getmPoint() {
        return mPoint;
    }

    public void setmPoint(Point mPoint) {
        this.mPoint = mPoint;
    }

    public Timestamp getmEnteringTime() {
        return mEnteringTime;
    }

    public void setmEnteringTime(Timestamp mEnteringTime) {
        this.mEnteringTime = mEnteringTime;
    }

    public List<Attendance> getmAttendence() {
        return mAttendence;
    }

    public void setmAttendence(List<Attendance> mAttendence) {
        this.mAttendence = mAttendence;
    }

    public List<Position> getmPosition() {
        return mPosition;
    }

    public void setmPosition(List<Position> mPosition) {
        this.mPosition = mPosition;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
