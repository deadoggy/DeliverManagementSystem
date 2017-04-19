package com.Deliver.model;

import javax.persistence.*;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name="day_form")
public class DayForm {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "day_form_id", unique = true, nullable = false)
    private String mDay_form_id;

    @Column(name = "year", nullable = false)
    private int mYear;

    @Column(name = "month", nullable = false)
    private int mMonth;

    @Column(name = "day",nullable = false)
    private int mDay;

    @Column(name = "sum", nullable = false)
    private int mSum;

    @Column(name = "company_id", nullable = false)
    private String mCompany_id;//foreign

    public DayForm(){

    }

    public DayForm(String mDay_form_id, int mYear, int mMonth, int mDay, int mSum, String mCompany_id) {
        this.mDay_form_id = mDay_form_id;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
        this.mSum = mSum;
        this.mCompany_id = mCompany_id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDay_form_id() {
        return mDay_form_id;
    }

    public void setmDay_form_id(String mDay_form_id) {
        this.mDay_form_id = mDay_form_id;
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
