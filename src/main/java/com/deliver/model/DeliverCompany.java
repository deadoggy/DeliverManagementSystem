package com.deliver.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name = "deliver_company")
public class DeliverCompany {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int mId;

    @Column(name="company_id",unique = true,nullable = false)
    private String mCompanyId;

    @Column(name = "name",unique = true,nullable = false)
    private String mName;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Package> mPackage;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<YearForm> mYearForm;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<MonthForm> mMonthForm;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<DayForm> mDayForm;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<HourForm> mHourForm;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<DeliverCompanyBill> mBills;


    public DeliverCompany(){
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmCompanyId() {
        return mCompanyId;
    }

    public void setmCompanyId(String mCompanyId) {
        this.mCompanyId = mCompanyId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Set<Package> getmPackage() {
        return mPackage;
    }

    public void setmPackage(Set<Package> mPackage) {
        this.mPackage = mPackage;
    }

    public Set<YearForm> getmYearForm() {
        return mYearForm;
    }

    public void setmYearForm(Set<YearForm> mYearForm) {
        this.mYearForm = mYearForm;
    }

    public Set<MonthForm> getmMonthForm() {
        return mMonthForm;
    }

    public void setmMonthForm(Set<MonthForm> mMonthForm) {
        this.mMonthForm = mMonthForm;
    }

    public Set<DayForm> getmDayForm() {
        return mDayForm;
    }

    public void setmDayForm(Set<DayForm> mDayForm) {
        this.mDayForm = mDayForm;
    }

    public Set<HourForm> getmHourForm() {
        return mHourForm;
    }

    public void setmHourForm(Set<HourForm> mHourForm) {
        this.mHourForm = mHourForm;
    }

    public Set<DeliverCompanyBill> getmBills() {
        return mBills;
    }

    public void setmBills(Set<DeliverCompanyBill> mBills) {
        this.mBills = mBills;
    }
}
