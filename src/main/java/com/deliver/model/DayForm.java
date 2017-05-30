package com.deliver.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

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

    @Column(name = "date", nullable = false)
    private Date mDate;

    @Column(name = "year", nullable = false)
    private int mYear;

    @Column(name = "month", nullable = false)
    private int mMonth;

    @Column(name = "day",nullable = false)
    private int mDay;

    @Column(name = "sum", nullable = false)
    private int mSum;

    @ManyToOne(cascade = {CascadeType.ALL})
    private DeliverCompany mCompany;//foreign

    public DayForm(){

    }

    public DayForm(Date mDate, int mYear, int mMonth, int mDay, int mSum, DeliverCompany mCompany) {
        this.mDate = mDate;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
        this.mSum = mSum;
        this.mCompany = mCompany;
    }

    public DayForm(int mYear, int mMonth, int mDay, int mSum, DeliverCompany mCompany) {
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
        this.mSum = mSum;
        this.mCompany = mCompany;
        Calendar c = Calendar.getInstance();
        c.set(mYear,mMonth-1,mDay);
        this.mDate = new Date(c.getTimeInMillis());
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
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

    public DeliverCompany getmCompany() {
        return mCompany;
    }

    public void setmCompany(DeliverCompany mCompany) {
        this.mCompany = mCompany;
    }
}
