package com.deliver.model;

import javax.persistence.*;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name="hour_form")
public class HourForm {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private DeliverCompany mCompany;//foreign

    public HourForm(){

    }

    public HourForm(int mYear, int mMonth, int mDay, int mHour, int mSum, DeliverCompany mCompany) {
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
        this.mHour = mHour;
        this.mSum = mSum;
        this.mCompany = mCompany;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
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

    public DeliverCompany getmCompany() {
        return mCompany;
    }

    public void setmCompany(DeliverCompany mCompany) {
        this.mCompany = mCompany;
    }
}
