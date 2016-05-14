package com.noiztezk.db;

import com.noiztezk.db.DzikirDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

/**
 * Created by hafizhhabiby on 5/5/16.
 */
@Table(database = DzikirDatabase.class)
@Parcel(analyze={Person.class})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (loginTimeStamp != person.loginTimeStamp) return false;
        if (logoutTimeStamp != person.logoutTimeStamp) return false;
        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (age != null ? !age.equals(person.age) : person.age != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        return deviceId != null ? deviceId.equals(person.deviceId) : person.deviceId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (int) (loginTimeStamp ^ (loginTimeStamp >>> 32));
        result = 31 * result + (int) (logoutTimeStamp ^ (logoutTimeStamp >>> 32));
        return result;
    }
}
