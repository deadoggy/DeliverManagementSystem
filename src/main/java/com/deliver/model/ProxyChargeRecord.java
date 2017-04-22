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


    @OneToOne
    private Package mPackage;// foreign key

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private DeliverCompany mCompany;// foreign key

    @Column(name = "fee", nullable = false)
    private double mFee;

    @Column(name = "send_receive", nullable = false)
    private boolean mSendOrReceive;

    @Column(name = "time", nullable = false)
    private Timestamp mTime;

    public ProxyChargeRecord() {}

    public ProxyChargeRecord(Package mPackage, DeliverCompany mCompany,
                             double mFee, boolean mSendOrReceive, Timestamp mTime) {
        this.mPackage = mPackage;
        this.mCompany = mCompany;
        this.mFee = mFee;
        this.mSendOrReceive = mSendOrReceive;
        this.mTime = mTime;
    }

    public Package getmPackage() {
        return mPackage;
    }

    public void setmPackage(Package mPackage) {
        this.mPackage = mPackage;
    }

    public DeliverCompany getmCompany() {
        return mCompany;
    }

    public void setmCompany(DeliverCompany mCompany) {
        this.mCompany = mCompany;
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
