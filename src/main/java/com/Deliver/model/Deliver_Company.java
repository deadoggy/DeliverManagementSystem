package com.Deliver.model;

import javax.persistence.*;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name = "deliver_company")
public class Deliver_Company {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int mId;

    @Column(name="company_id",unique = true,nullable = false)
    private String mCompany_id;

    @Column(name = "name",nullable = false)
    private String mName;

    public Deliver_Company(){

    }

    public Deliver_Company(String mCompany_id, String mName) {
        this.mCompany_id = mCompany_id;
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmCompany_id() {
        return mCompany_id;
    }

    public void setmCompany_id(String mCompany_id) {
        this.mCompany_id = mCompany_id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
