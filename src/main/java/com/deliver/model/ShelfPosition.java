package com.deliver.model;

import javax.persistence.*;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "shelf_position")
public class ShelfPosition {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "empty_full", nullable = false)
    private boolean mEmptyOrFull; // constant.Constant

    @Column(name = "layer_index", nullable = false)
    private int mLayer;

    @Column(name = "column_index", nullable = false)
    private int mColumn;

    public ShelfPosition() {}

    public ShelfPosition(boolean mEmptyOrFull, int mLayer, int mColumn) {
        this.mEmptyOrFull = mEmptyOrFull;
        this.mLayer = mLayer;
        this.mColumn = mColumn;
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
}
