package com.deliver.model;

import javax.persistence.*;

/**
 * Created by deadoggy on 17-4-18.
 */
@Entity
@Table(name = "smart_cupboard")
public class SmartCupboard {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int mId;

    @Column(name = "cupboard", nullable = false, unique = true)
    private String mCupboardId;

    @Column(name = "empty_sum", nullable = false)
    private int mEmptySum;

    @Column(name = "position_sum", nullable = false)
    private int mPositionSum;

    public SmartCupboard() {}

    public SmartCupboard(String mCupboardId, int mEmptySum, int mPositionSum) {
        this.mCupboardId = mCupboardId;
        this.mEmptySum = mEmptySum;
        this.mPositionSum = mPositionSum;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmCupboardId() {
        return mCupboardId;
    }

    public void setmCupboardId(String mCupboardId) {
        this.mCupboardId = mCupboardId;
    }

    public int getmEmptySum() {
        return mEmptySum;
    }

    public void setmEmptySum(int mEmptySum) {
        this.mEmptySum = mEmptySum;
    }

    public int getmPositionSum() {
        return mPositionSum;
    }

    public void setmPositionSum(int mPositionSum) {
        this.mPositionSum = mPositionSum;
    }
}
