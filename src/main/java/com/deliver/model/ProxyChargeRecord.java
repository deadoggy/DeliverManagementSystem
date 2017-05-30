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


    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private Package mPackage;// foreign key

    @Column(name = "fee", nullable = false)
    private double mFee;

    @Column(name = "send_receive", nullable = false)
    private boolean mSendorReceive;

    @Column(name = "time")
    private Timestamp mTime;

    public ProxyChargeRecord() {}

    public ProxyChargeRecord(Package mPackage,
                             double mFee, boolean mSendorReceive, Timestamp mTime) {
        this.mPackage = mPackage;
        this.mFee = mFee;
        this.mSendorReceive = mSendorReceive;
        this.mTime = mTime;
    }

    public Package getmPackage() {
        return mPackage;
    }

    public void setmPackage(Package mPackage) {
        this.mPackage = mPackage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }


    public double getmFee() {
        return mFee;
    }

    public void setmFee(double mFee) {
        this.mFee = mFee;
    }

    public boolean ismSendorReceive() {
        return mSendorReceive;
    }

    public void setmSendorReceive(boolean mSendorReceive) {
        this.mSendorReceive = mSendorReceive;
    }

    public Timestamp getmTime() {
        return mTime;
    }

    public void setmTime(Timestamp mTime) {
        this.mTime = mTime;
    }
}
