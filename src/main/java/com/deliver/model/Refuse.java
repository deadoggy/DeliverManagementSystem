package com.deliver.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by deadoggy on 17-5-17.
 */

@Entity
@Table(name = "refuse_list")
public class Refuse {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int mId;

    @Column(name = "package_id")
    private String mPackageId;

    @Column(name = "time")
    private Timestamp mTime;

    @Column(name = "reason")
    private String mReason;

    @Column(name = "date")
    private Date mDate;

    public Refuse() {
    }

    public Refuse(String mPackageId, Timestamp mTime, String mReason, Date mDate) {
        this.mPackageId = mPackageId;
        this.mTime = mTime;
        this.mReason = mReason;
        this.mDate = mDate;
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

    public Timestamp getmTime() {
        return mTime;
    }

    public void setmTime(Timestamp mTime) {
        this.mTime = mTime;
    }

    public String getmReason() {
        return mReason;
    }

    public void setmReason(String mReason) {
        this.mReason = mReason;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
