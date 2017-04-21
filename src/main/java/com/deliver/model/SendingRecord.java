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

    @Column(name = "package_number",nullable = false)
    private String mPackage_number;//foreign

    @Column(name="sender_name",nullable=false)
    private String mSender_name;//foreign

    @Column(name = "sender_tele",nullable = false)
    private String mSender_tele;//foreign

    @Column(name="company_id",nullable = false)
    private String mCompany_id;//foreign

    public SendingRecord(){

    }

    public SendingRecord(String mSending_record_id, String mPackage_number, String mSender_name, String mSender_tele, String mCompany_id) {
        this.mSending_record_id = mSending_record_id;
        this.mPackage_number = mPackage_number;
        this.mSender_name = mSender_name;
        this.mSender_tele = mSender_tele;
        this.mCompany_id = mCompany_id;
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

    public String getmPackage_number() {
        return mPackage_number;
    }

    public void setmPackage_number(String mPackage_number) {
        this.mPackage_number = mPackage_number;
    }

    public String getmSender_name() {
        return mSender_name;
    }

    public void setmSender_name(String mSender_name) {
        this.mSender_name = mSender_name;
    }

    public String getmSender_tele() {
        return mSender_tele;
    }

    public void setmSender_tele(String mSender_tele) {
        this.mSender_tele = mSender_tele;
    }

    public String getmCompany_id() {
        return mCompany_id;
    }

    public void setmCompany_id(String mCompany_id) {
        this.mCompany_id = mCompany_id;
    }
}
