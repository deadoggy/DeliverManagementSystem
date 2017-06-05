package com.deliver.model;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "cupboard_id", nullable = false, unique = true)
    private String mCupboardId;

    @Column(name = "empty_sum", nullable = false)
    private int mEmptySum;

    @Column(name = "position_sum", nullable = false)
    private int mPositionSum;

    @Column(name = "layer_sum", nullable = false)
    private int mLayerSum;

    @Column(name = "col_sum", nullable = false)
    private int mColumnSum;

    @OneToMany(mappedBy = "mCup",fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<StoragePosition> mPosition;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Point mPoint;

    public SmartCupboard() {}

    public SmartCupboard(String mCupboardId, int mEmptySum, int mPositionSum, int mLayerSum, int mColumnSum) {
        this.mCupboardId = mCupboardId;
        this.mEmptySum = mEmptySum;
        this.mPositionSum = mPositionSum;
        this.mLayerSum = mLayerSum;
        this.mColumnSum = mColumnSum;
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

    public int getmLayerSum() {
        return mLayerSum;
    }

    public void setmLayerSum(int mLayerSum) {
        this.mLayerSum = mLayerSum;
    }

    public int getmColumnSum() {
        return mColumnSum;
    }

    public void setmColumnSum(int mColumnSum) {
        this.mColumnSum = mColumnSum;
    }

    public List<StoragePosition> getmPosition() {
        return mPosition;
    }

    public void setmPosition(List<StoragePosition> mPosition) {
        this.mPosition = mPosition;
    }

    public Point getmPoint() {
        return mPoint;
    }

    public void setmPoint(Point mPoint) {
        this.mPoint = mPoint;
    }
}
