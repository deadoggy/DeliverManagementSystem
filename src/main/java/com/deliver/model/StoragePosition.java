package com.deliver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "shelf_position")
public class StoragePosition {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "cup_shelf")
    private boolean mCuporShelf;

    @Column(name = "empty_full", nullable = false)
    private boolean mEmptyorFull; // constant.Constant

    @Column(name = "layer_index", nullable = false)
    private int mLayer;

    @Column(name = "column_index", nullable = false)
    private int mColumn;

    @Column(name = "size", nullable = false)
    private int mSize;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private SmartCupboard mCup;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private Shelf mShelf;

    @OneToMany(mappedBy = "mId")
    private List<Package> mPackage;

    public StoragePosition() {
    }

    public StoragePosition(boolean mCuporShelf, boolean mEmptyorFull,
                           int mLayer, int mColumn, int mSize,
                           SmartCupboard mCup, Shelf mShelf) {
        this.mCuporShelf = mCuporShelf;
        this.mEmptyorFull = mEmptyorFull;
        this.mLayer = mLayer;
        this.mColumn = mColumn;
        this.mSize = mSize;
        this.mCup = mCup;
        this.mShelf = mShelf;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean ismCuporShelf() {
        return mCuporShelf;
    }

    public void setmCuporShelf(boolean mCuporShelf) {
        this.mCuporShelf = mCuporShelf;
    }

    public boolean ismEmptyorFull() {
        return mEmptyorFull;
    }

    public void setmEmptyorFull(boolean mEmptyorFull) {
        this.mEmptyorFull = mEmptyorFull;
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

    public SmartCupboard getmCup() {
        return mCup;
    }

    public void setmCup(SmartCupboard mCup) {
        this.mCup = mCup;
    }

    public Shelf getmShelf() {
        return mShelf;
    }

    public void setmShelf(Shelf mShelf) {
        this.mShelf = mShelf;
    }

    public List<Package> getmPackage() {
        return mPackage;
    }

    public void setmPackage(List<Package> mPackage) {
        this.mPackage = mPackage;
    }
}
