package com.deliver.model;


import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "mId")
    private List<Package> mPackage;

    @OneToMany(mappedBy = "mId")
    private List<YearForm> mYearForm;

    @OneToMany(mappedBy = "mId")
    private List<MonthForm> mMonthForm;

    @OneToMany(mappedBy = "mId")
    private List<DayForm> mDayForm;

    @OneToMany(mappedBy = "mId")
    private List<HourForm> mHourForm;

    @OneToMany(mappedBy = "mId")
    private List<DeliverCompanyBill> mBills;


    public DeliverCompany(){

    }

    public DeliverCompany(String mCompanyId, String mName, List<Package> mPackage) {
        this.mCompanyId = mCompanyId;
        this.mName = mName;
        this.mPackage = mPackage;
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

    public List<Package> getmPackage() {
        return mPackage;
    }

    public void setmPackage(List<Package> mPackage) {
        this.mPackage = mPackage;
    }

    public List<YearForm> getmYearForm() {
        return mYearForm;
    }

    public void setmYearForm(List<YearForm> mYearForm) {
        this.mYearForm = mYearForm;
    }

    public List<MonthForm> getmMonthForm() {
        return mMonthForm;
    }

    public void setmMonthForm(List<MonthForm> mMonthForm) {
        this.mMonthForm = mMonthForm;
    }

    public List<DayForm> getmDayForm() {
        return mDayForm;
    }

    public void setmDayForm(List<DayForm> mDayForm) {
        this.mDayForm = mDayForm;
    }

    public List<HourForm> getmHourForm() {
        return mHourForm;
    }

    public void setmHourForm(List<HourForm> mHourForm) {
        this.mHourForm = mHourForm;
    }

    public List<DeliverCompanyBill> getmBills() {
        return mBills;
    }

    public void setmBills(List<DeliverCompanyBill> mBills) {
        this.mBills = mBills;
    }
}
