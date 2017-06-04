package com.deliver.model;

import javax.persistence.*;
import java.util.Date;

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


    @Column(name = "sum", nullable = false)
    private int mSum;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private DeliverCompany mCompany;//foreign

    public DayForm(){

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
