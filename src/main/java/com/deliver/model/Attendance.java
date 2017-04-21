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

    @Column(name="attendance_id",unique = true,nullable = false)
    private String mAttendance_id;

    @Column(name="employee_id",nullable = false)//foreign
    private String mEmployee_id;

    @Column(name="begin_time",nullable = false)
    private Timestamp mBegin_time;

    @Column(name="end_time")
    private Timestamp mEnd_time;

    @Column(name="point_id",nullable = false)
    private String mPoint_id;

    public Attendance(){

    }

    public Attendance(String mAttendance_id, String mEmployee_id, Timestamp mBegin_time, Timestamp mEnd_time, String mPoint_id) {
        this.mAttendance_id = mAttendance_id;
        this.mEmployee_id = mEmployee_id;
        this.mBegin_time = mBegin_time;
        this.mEnd_time = mEnd_time;
        this.mPoint_id = mPoint_id;
    }

    public String getmEmployee_id() {
        return mEmployee_id;
    }

    public void setmEmployee_id(String mEmployee_id) {
        this.mEmployee_id = mEmployee_id;
    }

    public Timestamp getmBegin_time() {
        return mBegin_time;
    }

    public void setmBegin_time(Timestamp mBegin_time) {
        this.mBegin_time = mBegin_time;
    }

    public Timestamp getmEnd_time() {
        return mEnd_time;
    }

    public void setmEnd_time(Timestamp mEnd_time) {
        this.mEnd_time = mEnd_time;
    }

    public String getmPoint_id() {
        return mPoint_id;
    }

    public void setmPoint_id(String mPoint_id) {
        this.mPoint_id = mPoint_id;
    }
}
