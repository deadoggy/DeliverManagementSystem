package com.deliver.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

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
    private String mSendingRecordId;

    @ManyToOne
    private Package mPackage;//foreign

    @Column(name="sender_name",nullable=false)
    private String mSenderName;

    @Column(name = "sender_tele",nullable = false)
    private String mSenderTele;

    @Column(name="sender_address",nullable = false)
    private String mSenderAddress;

    @Column(name="sender_city",nullable = false)
    private String mSenderCity;

    @Column(name="sender_province",nullable = false)
    private String mSenderProvince;

    @Column(name="receiver_name",nullable = false)
    private String mReceiverName;

    @Column(name="receiver_tele",nullable = false)
    private String mReceiverTele;

    @Column(name="receiver_address",nullable = false)
    private String mReceiverAddress;

    @Column(name="receiver_city",nullable = false)
    private String mReceiverCity;

    @Column(name="receiver_province",nullable = false)
    private String mReceiverProvince;

    @Column(name="weight",nullable = false)
    private double mWeight;

    @Column(name="send_time")
    private Timestamp mSendTime;



    @ManyToOne
    private DeliverCompany mCompany;//foreign

    public SendingRecord(){

    }

    public SendingRecord(String mSendingRecordId, Package mPackage, String mSenderName, String mSenderTele,
                         String mSenderAddress, String mSenderCity, String mSenderProvince, String mReceiverName,
                         String mReceiverTele, String mReceiverAddress, String mReceiverCity,
                         String mReceiverProvince, double mWeight, Timestamp mSendTime, DeliverCompany mCompany) {
        this.mSendingRecordId = mSendingRecordId;
        this.mPackage = mPackage;
        this.mSenderName = mSenderName;
        this.mSenderTele = mSenderTele;
        this.mSenderAddress = mSenderAddress;
        this.mSenderCity = mSenderCity;
        this.mSenderProvince = mSenderProvince;
        this.mReceiverName = mReceiverName;
        this.mReceiverTele = mReceiverTele;
        this.mReceiverAddress = mReceiverAddress;
        this.mReceiverCity = mReceiverCity;
        this.mReceiverProvince = mReceiverProvince;
        this.mWeight = mWeight;
        this.mSendTime = mSendTime;
        this.mCompany = mCompany;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmSending_record_id() {
        return mSendingRecordId;
    }

    public void setmSending_record_id(String mSending_record_id) {
        this.mSendingRecordId = mSending_record_id;
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

    public String getmSenderAddress() {
        return mSenderAddress;
    }

    public void setmSenderAddress(String mSenderAddress) {
        this.mSenderAddress = mSenderAddress;
    }

    public String getmSenderCity() {
        return mSenderCity;
    }

    public void setmSenderCity(String mSenderCity) {
        this.mSenderCity = mSenderCity;
    }

    public String getmSenderProvince() {
        return mSenderProvince;
    }

    public void setmSenderProvince(String mSenderProvince) {
        this.mSenderProvince = mSenderProvince;
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

    public String getmReceiverAddress() {
        return mReceiverAddress;
    }

    public void setmReceiverAddress(String mReceiverAddress) {
        this.mReceiverAddress = mReceiverAddress;
    }

    public String getmReceiverCity() {
        return mReceiverCity;
    }

    public void setmReceiverCity(String mReceiverCity) {
        this.mReceiverCity = mReceiverCity;
    }

    public String getmReceiverProvince() {
        return mReceiverProvince;
    }

    public void setmReceiverProvince(String mReceiverProvince) {
        this.mReceiverProvince = mReceiverProvince;
    }

    public double getmWeight() {
        return mWeight;
    }

    public void setmWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    public Timestamp getmSendTime() {
        return mSendTime;
    }

    public void setmSendTime(Timestamp mSendTime) {
        this.mSendTime = mSendTime;
    }

    public DeliverCompany getmCompany() {
        return mCompany;
    }

    public void setmCompany(DeliverCompany mCompany) {
        this.mCompany = mCompany;
    }


}
