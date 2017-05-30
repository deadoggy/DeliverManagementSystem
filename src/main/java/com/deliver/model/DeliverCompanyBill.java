package com.deliver.model;

import javax.persistence.*;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "deliver_company_bill")
public class DeliverCompanyBill {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int mId;

    @ManyToOne(cascade = {CascadeType.ALL})
    private DeliverCompany mCompany; // foreign key

    @Column(name = "year", nullable = false)
    private int mYear;

    @Column(name = "month", nullable = false)
    private int mMonth;

    @Column(name = "package_sum", nullable = false)
    private int mPackageSum;

    @Column(name = "per_package_fee", nullable = false)
    private double mPerPackageFee;

    @Column(name = "proxy_charge_times", nullable = false)
    private int mProxyChargeTimes;

    @Column(name = "per_proxy_charge_fee", nullable = false)
    private double mPerProxyChargeFee;

    @Column(name = "paid", nullable = false)
    private boolean mPaid;


    public DeliverCompanyBill() {}

    public DeliverCompanyBill(DeliverCompany mCompany, int mYear,
                              int mMonth, int mPackageSum, double mPerPackageFee,
                              int mProxyChargeTimes, double mPerProxyChargeFee, boolean mPaid) {
        this.mCompany = mCompany;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mPackageSum = mPackageSum;
        this.mPerPackageFee = mPerPackageFee;
        this.mProxyChargeTimes = mProxyChargeTimes;
        this.mPerProxyChargeFee = mPerProxyChargeFee;
        this.mPaid = mPaid;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public DeliverCompany getmCompany() {
        return mCompany;
    }

    public void setmCompany(DeliverCompany mCompany) {
        this.mCompany = mCompany;
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

    public int getmPackageSum() {
        return mPackageSum;
    }

    public void setmPackageSum(int mPackageSum) {
        this.mPackageSum = mPackageSum;
    }

    public double getmPerPackageFee() {
        return mPerPackageFee;
    }

    public void setmPerPackageFee(double mPerPackageFee) {
        this.mPerPackageFee = mPerPackageFee;
    }

    public int getmProxyChargeTimes() {
        return mProxyChargeTimes;
    }

    public void setmProxyChargeTimes(int mProxyChargeTimes) {
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
