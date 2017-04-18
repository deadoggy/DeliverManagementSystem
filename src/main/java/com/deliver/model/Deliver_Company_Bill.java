package com.deliver.model;

import javax.persistence.*;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "deliver_company_bill")
public class Deliver_Company_Bill {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long mId;

    @Column(name = "company_id", nullable = false)
    private String mCompanyId; // foreign key

    @Column(name = "year", nullable = false)
    private int mYear;

    @Column(name = "month", nullable = false)
    private int mMonth;

    @Column(name = "package_sum", nullable = false)
    private long mPackageSum;

    @Column(name = "per_package_fee", nullable = false)
    private double mPerPackageFee;

    @Column(name = "proxy_charge_times", nullable = false)
    private long mProxyChargeTimes;

    @Column(name = "per_proxy_charge_fee", nullable = false)
    private double mPerProxyChargeFee;

    @Column(name = "paid", nullable = false)
    private boolean mPaid;


    public Deliver_Company_Bill() {}

    public Deliver_Company_Bill(String mCompanyId, int mYear, int mMonth,
                                long mPackageSum, double mPerPackageFee,
                                long mProxyChargeTimes, double mPerProxyChargeFee,
                                boolean mPaid) {
        this.mCompanyId = mCompanyId;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mPackageSum = mPackageSum;
        this.mPerPackageFee = mPerPackageFee;
        this.mProxyChargeTimes = mProxyChargeTimes;
        this.mPerProxyChargeFee = mPerProxyChargeFee;
        this.mPaid = mPaid;
    }


    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmCompanyId() {
        return mCompanyId;
    }

    public void setmCompanyId(String mCompanyId) {
        this.mCompanyId = mCompanyId;
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

    public long getmPackageSum() {
        return mPackageSum;
    }

    public void setmPackageSum(long mPackageSum) {
        this.mPackageSum = mPackageSum;
    }

    public double getmPerPackageFee() {
        return mPerPackageFee;
    }

    public void setmPerPackageFee(double mPerPackageFee) {
        this.mPerPackageFee = mPerPackageFee;
    }

    public long getmProxyChargeTimes() {
        return mProxyChargeTimes;
    }

    public void setmProxyChargeTimes(long mProxyChargeTimes) {
        this.mProxyChargeTimes = mProxyChargeTimes;
    }

    public double getmPerProxyChargeFee() {
        return mPerProxyChargeFee;
    }

    public void setmPerProxyChargeFee(double mPerProxyChargeFee) {
        this.mPerProxyChargeFee = mPerProxyChargeFee;
    }

    public boolean ismPaid() {
        return mPaid;
    }

    public void setmPaid(boolean mPaid) {
        this.mPaid = mPaid;
    }
}
