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
@Parcel(analyze={Dzikir.class})
public class Dzikir extends BaseModel {
    @Column
    @PrimaryKey(
            autoincrement = true
    )
    Long id;

    @Column
    String dzikirName;

    @Column
    String arabicDzikirText;

    @Column
    int countDzikir;

    @Column
    long reminderTime;

    @Column
    String read;

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDzikirName() {
        return dzikirName;
    }

    public void setDzikirName(String dzikirName) {
        this.dzikirName = dzikirName;
    }

    public String getArabicDzikirText() {
        return arabicDzikirText;
    }

    public void setArabicDzikirText(String arabicDzikirText) {
        this.arabicDzikirText = arabicDzikirText;
    }

    public int getCountDzikir() {
        return countDzikir;
    }

    public void setCountDzikir(int countDzikir) {
        this.countDzikir = countDzikir;
    }

    public long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(long reminderTime) {
        this.reminderTime = reminderTime;
    }
}
