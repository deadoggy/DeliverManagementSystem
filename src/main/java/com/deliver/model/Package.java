package com.deliver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by deadoggy on 17-4-18.
 */
@Entity
public class Package {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long mId;

    @Column(name = "package_id", nullable = false)
    private String mPackageId;

    @Column(name = "mCompany", nullable = false)
    private String mCompany; // foreign key

    @Column(name = "receive_time", nullable = false)
    private Timestamp mReceiveTime;

    @Column(name = "proxy_charge_fee", nullable = false)
    private double mProxyChargeFee;

    @Column(name = "taken", nullable = false)
    private boolean mTaken;

    @Column(name = "taken_time")
    private Timestamp mTakenTime;

    @Column(name = "receiver_name", nullable = false)
    private String mReceiverName;

    @Column(name = "receiver_tele", nullable = false)
    private String mReceiverTele;

    @Column(name = "cup_shelf", nullable = false)
    private boolean mCupOrShelf; // true = cup; false = shelf

    @Column(name = "position_id", nullable = false)
    private long mPositionId; // foreign ??


    public Package() {}

    public Package(String mPackageId, String mCompany, Timestamp mReceiveTime,
                   double mProxyChargeFee, boolean mTaken, Timestamp mTakenTime,
                   String mReceiverName, String mReceiverTele, boolean mCupOrShelf, long mPositionId) {
        this.mPackageId = mPackageId;
        this.mCompany = mCompany;
        this.mReceiveTime = mReceiveTime;
        this.mProxyChargeFee = mProxyChargeFee;
        this.mTaken = mTaken;
        this.mTakenTime = mTakenTime;
        this.mReceiverName = mReceiverName;
        this.mReceiverTele = mReceiverTele;
        this.mCupOrShelf = mCupOrShelf;
        this.mPositionId = mPositionId;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmPackageId() {
        return mPackageId;
    }

    public void setmPackageId(String mPackageId) {
        this.mPackageId = mPackageId;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public Timestamp getmReceiveTime() {
        return mReceiveTime;
    }

    public void setmReceiveTime(Timestamp mReceiveTime) {
        this.mReceiveTime = mReceiveTime;
    }

    public double getmProxyChargeFee() {
        return mProxyChargeFee;
    }

    public void setmProxyChargeFee(double mProxyChargeFee) {
        this.mProxyChargeFee = mProxyChargeFee;
    }

    public boolean ismTaken() {
        return mTaken;
    }

    public void setmTaken(boolean mTaken) {
        this.mTaken = mTaken;
    }

    public Timestamp getmTakenTime() {
        return mTakenTime;
    }

    public void setmTakenTime(Timestamp mTakenTime) {
        this.mTakenTime = mTakenTime;
    }

    public String getmReceiverName() {
        return mReceiverName;
    }

    public void setmReceiverName(String mReceiverName) {
        this.mReceiverName = mReceiverName;
    }

    public String getmReceiverTele() {
        return mReceiverTele;
    }

    public void setmReceiverTele(String mReceiverTele) {
        this.mReceiverTele = mReceiverTele;
    }

    public boolean ismCupOrShelf() {
        return mCupOrShelf;
    }

    public void setmCupOrShelf(boolean mCupOrShelf) {
        this.mCupOrShelf = mCupOrShelf;
    }

    public long getmPositionId() {
        return mPositionId;
    }

    public void setmPositionId(long mPositionId) {
        this.mPositionId = mPositionId;
    }
}
