package com.deliver.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "proxy_charge_record")
public class ProxyChargeRecord {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int mId;

    @Column(name = "package_id", nullable = false)
    private String mPackageId;// foreign key

    @Column(name = "company_id", nullable = false)
    private String mCompanyId;// foreign key

    @Column(name = "fee", nullable = false)
    private double mFee;

    @Column(name = "send_receive", nullable = false)
    private boolean mSendOrReceive;

    @Column(name = "time", nullable = false)
    private Timestamp mTime;

    public ProxyChargeRecord() {}

    public ProxyChargeRecord(String mPackageId, String mCompanyId, double mFee,
                             boolean mSendOrReceive, Timestamp mTime) {
        this.mPackageId = mPackageId;
        this.mCompanyId = mCompanyId;
        this.mFee = mFee;
        this.mSendOrReceive = mSendOrReceive;
        this.mTime = mTime;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmPackageId() {
        return mPackageId;
    }

    public void setmPackageId(String mPackageId) {
        this.mPackageId = mPackageId;
    }

    public String getmCompanyId() {
        return mCompanyId;
    }

    public void setmCompanyId(String mCompanyId) {
        this.mCompanyId = mCompanyId;
    }

    public double getmFee() {
        return mFee;
    }

    public void setmFee(double mFee) {
        this.mFee = mFee;
    }

    public boolean ismSendOrReceive() {
        return mSendOrReceive;
    }

    public void setmSendOrReceive(boolean mSendOrReceive) {
        this.mSendOrReceive = mSendOrReceive;
    }

    public Timestamp getmTime() {
        return mTime;
    }

    public void setmTime(Timestamp mTime) {
        this.mTime = mTime;
    }
}
