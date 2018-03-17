package com.noiztezk.db;

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
    long id;

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

    public Dzikir(){}

    public Dzikir(Dzikir dzikir){
        setRead(dzikir.getRead());
        setReminderTime(dzikir.getReminderTime());
        setCountDzikir(dzikir.getCountDzikir());
        setArabicDzikirText(dzikir.getArabicDzikirText());
        setDzikirName(dzikir.getDzikirName());
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dzikir dzikir = (Dzikir) o;

        if (id != dzikir.id) return false;
        if (countDzikir != dzikir.countDzikir) return false;
        if (reminderTime != dzikir.reminderTime) return false;
        if (dzikirName != null ? !dzikirName.equals(dzikir.dzikirName) : dzikir.dzikirName != null)
            return false;
        if (arabicDzikirText != null ? !arabicDzikirText.equals(dzikir.arabicDzikirText) : dzikir.arabicDzikirText != null)
            return false;
        return read != null ? read.equals(dzikir.read) : dzikir.read == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dzikirName != null ? dzikirName.hashCode() : 0);
        result = 31 * result + (arabicDzikirText != null ? arabicDzikirText.hashCode() : 0);
        result = 31 * result + countDzikir;
        result = 31 * result + (int) (reminderTime ^ (reminderTime >>> 32));
        result = 31 * result + (read != null ? read.hashCode() : 0);
        return result;
    }
}
