package com.deliver.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by deadoggy on 17-4-18.
 */

@Entity
@Table(name = "business_point")
public class Point {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int mId;

    @Column(name = "business_point_id", nullable = false, unique = true)
    private String mBusinessPointId;

    @Column(name = "name", nullable = false)
    private String mName;

    @Column(name = "province", nullable = false)
    private String mProvince;

    @Column(name = "city", nullable = false)
    private String mCity;

    @Column(name = "county", nullable = false)
    private String mCounty;

    @Column(name = "road", nullable = false)
    private String mRoad;

    @Column(name = "number", nullable = false)
    private String mNumber;

    @OneToMany(mappedBy = "mEmployeeId",/*fetch = FetchType.EAGER,*/ cascade = {CascadeType.ALL})
    private Set<Employee> mEmployee;

    @OneToMany(mappedBy = "mPoint",/*fetch = FetchType.EAGER,*/ cascade = {CascadeType.ALL})
    private Set<Shelf> mShelf;

    @OneToMany(mappedBy = "mPoint",/*fetch = FetchType.EAGER,*/ cascade = {CascadeType.ALL})
    private Set<SmartCupboard> mSmartCupboard;

    public Point() {}

    public Point(String mBusinessPointId, String mName,
                 String mProvince, String mCity,
                 String mCounty, String mRoad, String mNumber) {
        this.mBusinessPointId = mBusinessPointId;
        this.mName = mName;
        this.mProvince = mProvince;
        this.mCity = mCity;
        this.mCounty = mCounty;
        this.mRoad = mRoad;
        this.mNumber = mNumber;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmBusinessPointId() {
        return mBusinessPointId;
    }

    public void setmBusinessPointId(String mBusinessPointId) {
        this.mBusinessPointId = mBusinessPointId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmProvince() {
        return mProvince;
    }

    public void setmProvince(String mProvince) {
        this.mProvince = mProvince;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmRoad() {
        return mRoad;
    }

    public void setmRoad(String mRoad) {
        this.mRoad = mRoad;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmCounty() {
        return mCounty;
    }

    public void setmCounty(String mCounty) {
        this.mCounty = mCounty;
    }

    public Set<Employee> getmEmployee() {
        return mEmployee;
    }

    public void setmEmployee(Set<Employee> mEmployee) {
        this.mEmployee = mEmployee;
    }

    public Set<Shelf> getmShelf() {
        return mShelf;
    }

    public void setmShelf(Set<Shelf> mShelf) {
        this.mShelf = mShelf;
    }

    public Set<SmartCupboard> getmSmartCupboard() {
        return mSmartCupboard;
    }

    public void setmSmartCupboard(Set<SmartCupboard> mSmartCupboard) {
        this.mSmartCupboard = mSmartCupboard;
    }
}
