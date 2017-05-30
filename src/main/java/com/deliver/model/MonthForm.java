package com.deliver.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name = "month_form")
public class MonthForm {
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

    @Column(name = "sum", nullable = false)
    private int mSum;

    @ManyToOne(cascade = {CascadeType.ALL})
    private DeliverCompany mCompany;//foreign

    public MonthForm() {

    }

    public MonthForm(Date mDate, int mYear, int mMonth, int mSum, DeliverCompany mCompany) {
        this.mDate = mDate;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mSum = mSum;
        this.mCompany = mCompany;
    }

    public MonthForm(int mYear, int mMonth, int mSum, DeliverCompany mCompany) {
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mSum = mSum;
        this.mCompany = mCompany;
        Calendar c = Calendar.getInstance();
        c.set(mYear,mMonth-1, 1);
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
