package com.deliver.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name="year_form")
public class YearForm {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int mId;

    @Column(name = "date", nullable = false)
    private Date mDate;

    @Column(name="year_form_id",unique = true,nullable = false)
    private String mYearFormId;

    @Column(name="year",nullable = false)
    private int mYear;

    @Column(name="sum",nullable = false)
    private int mSum;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private DeliverCompany mCompany;//foreign

    public YearForm(){

    }

    public YearForm(Date mDate, String mYearFormId, int mYear, int mSum, DeliverCompany mCompany) {
        this.mDate = mDate;
        this.mYearFormId = mYearFormId;
        this.mYear = mYear;
        this.mSum = mSum;
        this.mCompany = mCompany;
    }

    public YearForm(String mYearFormId, int mYear, int mSum, DeliverCompany mCompany) {
        this.mYearFormId = mYearFormId;
        this.mYear = mYear;
        this.mSum = mSum;
        this.mCompany = mCompany;
        Calendar c = Calendar.getInstance();
        c.set(mYear,0,1);
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

    public String getmYearFormId() {
        return mYearFormId;
    }

    public void setmYearFormId(String mYearFormId) {
        this.mYearFormId = mYearFormId;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
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
