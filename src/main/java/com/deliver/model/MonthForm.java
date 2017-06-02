package com.deliver.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name = "month_form")
public class        MonthForm {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "date", nullable = false)
    private Date mDate;

    @Column(name = "sum", nullable = false)
    private int mSum;

    @ManyToOne(cascade = {CascadeType.ALL})
    private DeliverCompany mCompany;//foreign

    public MonthForm() {

    }

    public MonthForm(Date mDate, int mSum, DeliverCompany mCompany) {
        this.mDate = mDate;
        this.mSum = mSum;
        this.mCompany = mCompany;
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
