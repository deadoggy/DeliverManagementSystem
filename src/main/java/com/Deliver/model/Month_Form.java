package com.Deliver.model;

import javax.persistence.*;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name = "month_form")
public class Month_Form {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "month_form_id", unique = true, nullable = false)
    private String mMonth_form_id;

    @Column(name = "year", nullable = false)
    private int mYear;

    @Column(name = "month", nullable = false)
    private int mMonth;

    @Column(name = "sum", nullable = false)
    private int mSum;

    @Column(name = "company_id", nullable = false)
    private String mCompany_id;//foreign

    public Month_Form() {

    }

    public Month_Form(String mMonth_form_id, int mYear, int mMonth, int mSum, String mCompany_id) {
        this.mMonth_form_id = mMonth_form_id;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mSum = mSum;
        this.mCompany_id = mCompany_id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmMonth_form_id() {
        return mMonth_form_id;
    }

    public void setmMonth_form_id(String mMonth_form_id) {
        this.mMonth_form_id = mMonth_form_id;
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

    public String getmCompany_id() {
        return mCompany_id;
    }

    public void setmCompany_id(String mCompany_id) {
        this.mCompany_id = mCompany_id;
    }
}
