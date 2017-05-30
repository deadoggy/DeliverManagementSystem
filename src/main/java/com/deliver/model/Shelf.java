package com.deliver.model;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "empty_sum")
    private int mEmptySum;

    @Column(name = "position_sum")
    private int mPositionSum;

    @Column(name = "layer_sum", nullable = false)
    private int mLayerSum;

    @Column(name = "col_sum", nullable = false)
    private int mColumnSum;

    @OneToMany(mappedBy = "mId",fetch = FetchType.EAGER)
    private List<StoragePosition> mPosition;

    public Shelf() {}

    public Shelf(String mShelfId, int mEmptySum, int mPositionSum, int mLayerSum, int mColumnSum) {
        this.mShelfId = mShelfId;
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
}
