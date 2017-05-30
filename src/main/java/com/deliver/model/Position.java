package com.deliver.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 91574 on 2017/4/18.
 */
@Entity
@Table(name = "position")
public class Position {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int mId;

    @Column(name="position_id",unique = true,nullable = false)
    private String mPositionId;

    @Column(name = "name",nullable = false)
    private String mName;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Employee> mEmployee;

    public Position(){

    }

    public Position(String mPositionId, String mName) {
        this.mPositionId = mPositionId;
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmPositionId() {
        return mPositionId;
    }

    public void setmPositionId(String mPositionId) {
        this.mPositionId = mPositionId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public List<Employee> getmEmployee() {
        return mEmployee;
    }

    public void setmEmployee(List<Employee> mEmployee) {
        this.mEmployee = mEmployee;
    }
}
