package com.deliver.model;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name="attendance")
public class Attendance {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int mId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Employee mEmployee;

    @Column(name="begin_time",nullable = false)
    private Timestamp mBeginTime;

    @Column(name="end_time")
    private Timestamp mEndTime;

    @Column(name="point_id",nullable = false)
    private String mPointId;

    public Attendance(){

    }

    public Attendance(Employee mEmployee, Timestamp mBeginTime, Timestamp mEndTime, String mPointId) {
        this.mEmployee = mEmployee;
        this.mBeginTime = mBeginTime;
        this.mEndTime = mEndTime;
        this.mPointId = mPointId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Employee getmEmployee() {
        return mEmployee;
    }

    public void setmEmployee(Employee mEmployee) {
        this.mEmployee = mEmployee;
    }

    public Timestamp getmBeginTime() {
        return mBeginTime;
    }

    public void setmBeginTime(Timestamp mBeginTime) {
        this.mBeginTime = mBeginTime;
    }

    public Timestamp getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(Timestamp mEndTime) {
        this.mEndTime = mEndTime;
    }

    public String getmPointId() {
        return mPointId;
    }

    public void setmPointId(String mPointId) {
        this.mPointId = mPointId;
    }
}
