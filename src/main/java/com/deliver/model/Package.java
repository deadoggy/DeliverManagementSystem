package com.deliver.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by deadoggy on 17-4-18.
 */
@Entity
@Table(name = "package")
public class Package {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int mId;

    @Column(name = "package_id", nullable = false, unique = true)
    private String mPackageId;

    @Column(name = "receive_time")
    private Timestamp mReceiveTime;

    @Column(name = "proxy_charge_fee")
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
    private boolean mCupOrShelf; // constant.Constant

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private StoragePosition mPosition;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private DeliverCompany mCompany; // foreign key

    public Package() {}


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

    public DeliverCompany getmCompany() {
        return mCompany;
    }

    public void setmCompany(DeliverCompany mCompany) {
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

    public StoragePosition getmPosition() {
        return mPosition;
    }

    public void setmPosition(StoragePosition mPosition) {
        this.mPosition = mPosition;
    }
}
