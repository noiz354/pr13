package com.noiztezk.pr13.model;

/**
 * Created by Normansyah Putra on 7/25/2015.
 * each time user tap count then use this class
 * to record location, what time
 */

import com.noiztezk.pr13.interfaces.DzkrCountModel;

import org.parceler.Parcel;
import org.parceler.Transient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Normansyah Putra on 7/27/2015.
 */
@Parcel
public class DzkrCount{
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    int count; // count by user
    ArrayList<Date> curDate;// TODO currently not parcelable
    ArrayList<String> visCurDate; // TODO currently not parcelable
    double latitude, langitude; // TODO currently not use
    public Dzikir dzkrRef;

    public DzkrCount(){
        curDate = new ArrayList<>();
        visCurDate = new ArrayList<>();
    }

    @Transient
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
            if(count >= Integer.parseInt(dzkrRef.getCount())){
                model.updateUI("bebas");
            }else {
                model.updateUI(count);
            }
        }
    }

    public Dzikir mergeWithRef(){
        int measure = -1;
        if(Integer.parseInt(dzkrRef.getCount()) < 0)
            measure = this.count;
        else
            measure = Integer.parseInt(dzkrRef.getCount()) - this.count;

        Dzikir dzikir = new Dzikir();
        dzikir.setText(dzkrRef.text);
        dzikir.setAudio(dzkrRef.audio);
        dzikir.setCount(measure+"");
        return dzikir;
    }

    @Override
    public String toString() {
        return "DzkrCount{" +
                ", count=" + count +
                ", dzkrRef=" + dzkrRef +
                '}';
    }

    public int getCount() {
        return count;
    }
}
