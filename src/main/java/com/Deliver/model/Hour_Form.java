package com.Deliver.model;

import javax.persistence.*;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name="hour_form")
public class Hour_Form {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "hour_form_id", unique = true, nullable = false)
    private String mHour_form_id;

    @Column(name = "year", nullable = false)
    private int mYear;

    @Column(name = "month", nullable = false)
    private int mMonth;

    @Column(name = "day",nullable = false)
    private int mDay;

    @Column(name="hour",nullable = false)
    private int mHour;

    @Column(name = "sum", nullable = false)
    private int mSum;

    @Column(name = "company_id", nullable = false)
    private String mCompany_id;//foreign

    public Hour_Form(){

    }

    public Hour_Form(String mHour_form_id, int mYear, int mMonth, int mDay, int mHour, int mSum, String mCompany_id) {
        this.mHour_form_id = mHour_form_id;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
        this.mHour = mHour;
        this.mSum = mSum;
        this.mCompany_id = mCompany_id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmHour_form_id() {
        return mHour_form_id;
    }

    public void setmHour_form_id(String mHour_form_id) {
        this.mHour_form_id = mHour_form_id;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public int getmSum() {
        return mSum;
    }

    public void setmSum(int mSum) {
        this.mSum = mSum;
    }

    public String getmCompany_id() {
        return mCompany_id;
    }

    public void setmCompany_id(String mCompany_id) {
        this.mCompany_id = mCompany_id;
    }
}
