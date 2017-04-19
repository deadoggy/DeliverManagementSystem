package com.Deliver.model;

import javax.persistence.*;

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

    @Column(name="year_form_id",unique = true,nullable = false)
    private String mYear_form_id;

    @Column(name="year",nullable = false)
    private int mYear;

    @Column(name="sum",nullable = false)
    private int mSum;

    @Column(name="company_id",nullable = false)
    private String mCompany_id;//foreign

    public YearForm(){

    }

    public YearForm(String mYear_form_id, int mYear, int mSum, String mCompany_id) {
        this.mYear_form_id = mYear_form_id;
        this.mYear = mYear;
        this.mSum = mSum;
        this.mCompany_id = mCompany_id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmYear_form_id() {
        return mYear_form_id;
    }

    public void setmYear_form_id(String mYear_form_id) {
        this.mYear_form_id = mYear_form_id;
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

    public String getmCompany_id() {
        return mCompany_id;
    }

    public void setmCompany_id(String mCompany_id) {
        this.mCompany_id = mCompany_id;
    }
}
