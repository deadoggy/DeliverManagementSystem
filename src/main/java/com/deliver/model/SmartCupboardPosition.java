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
    private int mId;

    @Column(name = "empty_full", nullable = false)
    private boolean mEmptyOrFull; // constant.Constant

    @Column(name = "layer_index", nullable = false)
    private int mLayer;

    @Column(name = "column_index", nullable = false)
    private int mColumn;

    @Column(name = "size", nullable = false)
    private int mSize;

    public SmartCupboardPosition() {}

    public SmartCupboardPosition(boolean mEmptyOrFull, int mLayer, int mColumn, int mSize) {
        this.mEmptyOrFull = mEmptyOrFull;
        this.mLayer = mLayer;
        this.mColumn = mColumn;
        this.mSize = mSize;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean ismEmptyOrFull() {
        return mEmptyOrFull;
    }

    public void setmEmptyOrFull(boolean mEmptyOrFull) {
        this.mEmptyOrFull = mEmptyOrFull;
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

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }
}