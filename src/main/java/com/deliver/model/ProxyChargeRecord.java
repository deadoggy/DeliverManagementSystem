package com.deliver.model;

import javax.persistence.*;
import java.util.Date;
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


    @OneToOne
    private Package mPackage;// foreign key

    @Column(name = "fee", nullable = false)
    private double mFee;

    @Column(name = "send_receive", nullable = false)
    private boolean mSendorReceive;

    @Column(name = "date", nullable = false)
    private Date mDate;

    public ProxyChargeRecord() {}

    public ProxyChargeRecord(Package mPackage, double mFee, boolean mSendorReceive, Date mDate) {
        this.mPackage = mPackage;
        this.mFee = mFee;
        this.mSendorReceive = mSendorReceive;
        this.mDate = mDate;
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

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
