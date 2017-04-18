package com.deliver.model;

import javax.persistence.*;

/**
 * Created by deadoggy on 17-4-18.
 */
@Entity
@Table(name = "smart_cupboard_position")
public class SmartCupboardPosition {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long mId;

    @Column(name = "state", nullable = false)
    private boolean mState; // constant.Constant

    @Column(name = "layer", nullable = false)
    private int mLayer;

    @Column(name = "column", nullable = false)
    private int mColumn;

    public SmartCupboardPosition() {}

    public SmartCupboardPosition(boolean mState, int mLayer, int mColumn) {
        this.mState = mState;
        this.mLayer = mLayer;
        this.mColumn = mColumn;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public boolean ismState() {
        return mState;
    }

    public void setmState(boolean mState) {
        this.mState = mState;
    }

    public int getmLayer() {
        return mLayer;
    }

    public void setmLayer(int mLayer) {
        this.mLayer = mLayer;
    }

    public int getmColumn() {
        return mColumn;
    }

    public void setmColumn(int mColumn) {
        this.mColumn = mColumn;
    }
}
