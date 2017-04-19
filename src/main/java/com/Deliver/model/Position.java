package com.Deliver.model;

import javax.persistence.*;

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
    private String position_id;

    @Column(name = "name",nullable = false)
    private String mName;

    public Position(){

    }

    public Position(String position_id, String mName) {
        this.position_id = position_id;
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
