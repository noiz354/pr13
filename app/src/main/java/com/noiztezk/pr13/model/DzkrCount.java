package com.noiztezk.pr13.model;

/**
 * Created by Normansyah Putra on 7/25/2015.
 * each time user tap count then use this class
 * to record location, what time
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.noiztezk.pr13.interfaces.DzkrCountModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Normansyah Putra on 7/27/2015.
 */
public class DzkrCount implements Parcelable{
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    int count; // count by user
    ArrayList<Date> curDate;// TODO currently not parcelable
    ArrayList<String> visCurDate; // TODO currently not parcelable
    double latitude, langitude; // TODO currently not use
    public Dzkr dzkrRef;

    public DzkrCount(){
        curDate = new ArrayList<>();
        visCurDate = new ArrayList<>();
    }

    DzkrCountModel model;

    public void setModel(DzkrCountModel model) {
        this.model = model;
    }

    /**
     * do increment here
     */
    public void incrementCount(){
        count++;// increment count
        Date curDateTime = new Date();
        curDate.add(curDateTime);// save current date as date object
        visCurDate.add(dateFormat.format(curDateTime)); // save current date and time as string

        if(model!=null){
            if(count >= dzkrRef.count){
                model.updateUI("bebas");
            }else {
                model.updateUI(count);
            }
        }
    }

    public Dzkr mergeWithRef(){
        int measure = -1;
        if(dzkrRef.count < 0)
            measure = this.count;
        else
            measure = dzkrRef.count - this.count;
        return new Dzkr(dzkrRef.text, dzkrRef.audio, measure);
    }

    @Override
    public String toString() {
        return "DzkrCount{" +
                ", count=" + count +
                ", dzkrRef=" + dzkrRef +
                '}';
    }

    // Parcelling part
    public DzkrCount(Parcel in){

        this.dzkrRef = in.readParcelable(Dzkr.class.getClassLoader());
        this.count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(dzkrRef, i);
        parcel.writeInt(count);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DzkrCount createFromParcel(Parcel in) {
            return new DzkrCount(in);
        }

        public DzkrCount[] newArray(int size) {
            return new DzkrCount[size];
        }
    };

    public int getCount() {
        return count;
    }
}
