package com.deliver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "storageposition")
public class StoragePosition {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int mId;

    @Column(name = "cup_shelf")
    private boolean mCuporShelf;

    @Column(name = "empty_full", nullable = false)
    private boolean mEmpty; // constant.Constant

    @Column(name = "layer_index", nullable = false)
    private int mLayer;

    @Column(name = "column_index", nullable = false)
    private int mColumn;

    @Column(name="identify_code")
    private String mIdentifyCode;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private SmartCupboard mCup;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Shelf mShelf;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Package> mPackage=new ArrayList<Package>();

    public StoragePosition() {
    }

    public StoragePosition(boolean mCuporShelf, boolean mEmpty, int mLayer, int mColumn,
                           String mIdentifyCode, SmartCupboard mCup, Shelf mShelf,
                           List<Package> mPackage) {
        this.mCuporShelf = mCuporShelf;
        this.mEmpty = mEmpty;
        this.mLayer = mLayer;
        this.mColumn = mColumn;
        this.mIdentifyCode = mIdentifyCode;
        this.mCup = mCup;
        this.mShelf = mShelf;
        this.mPackage = mPackage;
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

    public boolean ismEmpty() {
        return mEmpty;
    }

    public void setmEmpty(boolean mEmpty) {
        this.mEmpty = mEmpty;
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

    public String getmIdentifyCode() {
        return mIdentifyCode;
    }

    public void setmIdentifyCode(String mIdentifyCode) {
        this.mIdentifyCode = mIdentifyCode;
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
