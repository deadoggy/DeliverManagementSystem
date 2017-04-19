package com.deliver.model;

import javax.persistence.*;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "shelf_id", nullable = false, unique = true)
    private String mShelfId;

    @Column(name = "empty_sum", nullable = false)
    private int mEmptySum;

    @Column(name = "position_sum", nullable = false)
    private int mPositionSum;

    public Shelf() {}

    public Shelf(String mShelfId, int mEmptySum, int mPositionSum) {
        this.mShelfId = mShelfId;
        this.mEmptySum = mEmptySum;
        this.mPositionSum = mPositionSum;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmShelfId() {
        return mShelfId;
    }

    public void setmShelfId(String mShelfId) {
        this.mShelfId = mShelfId;
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
