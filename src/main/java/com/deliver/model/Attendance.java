package com.deliver.model;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Point mPoint;
//    @Column(name="point_id",nullable = false)
//    private String mPointId;

    public Attendance(){

    }

    public Attendance(Employee mEmployee, Timestamp mBeginTime, Timestamp mEndTime, Point mPoint) {
        this.mEmployee = mEmployee;
        this.mBeginTime = mBeginTime;
        this.mEndTime = mEndTime;
        this.mPoint = mPoint;
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

    public Point getmPoint() {
        return mPoint;
    }

    public void setmPoint(Point mPoint) {
        this.mPoint = mPoint;
    }
}
