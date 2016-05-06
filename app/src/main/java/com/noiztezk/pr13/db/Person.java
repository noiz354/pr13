package com.noiztezk.pr13.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by hafizhhabiby on 5/5/16.
 */
@Table(database = DzikirDatabase.class)
public class Person  extends BaseModel {

    @Column
    @PrimaryKey(
            autoincrement = true
    )
    Long id;

    @Column
    String name;

    @Column
    String age;

    @Column
    String email;

    @Column
    String deviceId;

    @Column
    long loginTimeStamp;

    @Column
    long logoutTimeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getLoginTimeStamp() {
        return loginTimeStamp;
    }

    public void setLoginTimeStamp(long loginTimeStamp) {
        this.loginTimeStamp = loginTimeStamp;
    }

    public long getLogoutTimeStamp() {
        return logoutTimeStamp;
    }

    public void setLogoutTimeStamp(long logoutTimeStamp) {
        this.logoutTimeStamp = logoutTimeStamp;
    }
}
