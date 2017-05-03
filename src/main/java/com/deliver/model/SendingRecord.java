package com.deliver.model;

import javax.persistence.*;

/**
 * Created by 91574 on 2017/4/19.
 */
@Entity
@Table(name="sending_record")
public class SendingRecord {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int mId;

    @Column(name = "sending_record_id",unique = true,nullable = false)
    private String mSending_record_id;

    @ManyToOne
    private Package mPackage;//foreign

    @Column(name="sender_name",nullable=false)
    private String mSenderName;

    @Column(name = "sender_tele",nullable = false)
    private String mSenderTele;

    @ManyToOne
    private DeliverCompany mCompany;//foreign

    public SendingRecord(){

    }

    public SendingRecord(String mSending_record_id, Package mPackage,
                         String mSenderName, String mSenderTele, DeliverCompany mCompany) {
        this.mSending_record_id = mSending_record_id;
        this.mPackage = mPackage;
        this.mSenderName = mSenderName;
        this.mSenderTele = mSenderTele;
        this.mCompany = mCompany;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmSending_record_id() {
        return mSending_record_id;
    }

    public void setmSending_record_id(String mSending_record_id) {
        this.mSending_record_id = mSending_record_id;
    }

    public Package getmPackage() {
        return mPackage;
    }

    public void setmPackage(Package mPackage) {
        this.mPackage = mPackage;
    }

    public String getmSenderName() {
        return mSenderName;
    }

    public void setmSenderName(String mSenderName) {
        this.mSenderName = mSenderName;
    }

    public String getmSenderTele() {
        return mSenderTele;
    }

    public void setmSenderTele(String mSenderTele) {
        this.mSenderTele = mSenderTele;
    }

    public DeliverCompany getmCompany() {
        return mCompany;
    }

    public void setmCompany(DeliverCompany mCompany) {
        this.mCompany = mCompany;
    }
}
