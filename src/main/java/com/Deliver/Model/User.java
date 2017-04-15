package com.Deliver.Model;
import javax.persistence.*;


/**
 * Created by deadoggy on 17-4-16.
 */
@Table
@Entity
public class User {

    @Id
    @GeneratedValue
    private int mId;

    @Column(name="name")
    private String mName;

    public User() {
    }

    public User(String mName) {
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
