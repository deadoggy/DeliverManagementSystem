package com.deliver.model;

import javax.persistence.*;
import java.security.Timestamp;

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
    private String mEmployee_id;

    @Column(name="age",nullable = false)
    private int mAge;

    @Column(name="salary",nullable = false)
    private float mSalary;

    @Column(name="point_id",nullable = false)//foreign
    private String mPoint_id;

    @Column(name="entering_time",nullable = false)
    private Timestamp mEntering_time;

    public Employee(){

    }

    public Employee(String mEmployee_id, int mAge, float mSalary, String mPoint_id, Timestamp mEntering_time) {
        this.mEmployee_id = mEmployee_id;
        this.mAge = mAge;
        this.mSalary = mSalary;
        this.mPoint_id = mPoint_id;
        this.mEntering_time = mEntering_time;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmEmployee_id() {
        return mEmployee_id;
    }

    public void setmEmployee_id(String mEmployee_id) {
        this.mEmployee_id = mEmployee_id;
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

    public String getmPoint_id() {
        return mPoint_id;
    }

    public void setmPoint_id(String mPoint_id) {
        this.mPoint_id = mPoint_id;
    }

    public Timestamp getmEntering_time() {
        return mEntering_time;
    }

    public void setmEntering_time(Timestamp mEntering_time) {
        this.mEntering_time = mEntering_time;
    }
}
