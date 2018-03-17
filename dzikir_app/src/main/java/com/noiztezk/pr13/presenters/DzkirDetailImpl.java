package com.noiztezk.pr13.presenters;

import android.os.Bundle;
import android.util.Log;

import com.noiztezk.db.Person;
import com.noiztezk.db.ReadDzikir;
import com.noiztezk.db.ReadDzikir_Table;
import com.noiztezk.pr13.HomeActivity;
import com.noiztezk.pr13.R;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.utils.Constants;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.parceler.Parcels;

import java.util.List;

import static com.noiztezk.pr13.view.DzkirDetailCounterFragment.getDate;
import static com.noiztezk.pr13.view.DzkirDetailCounterFragment.getTime;

/**
 * Created by noiz354 on 5/13/16.
 */
public class DzkirDetailImpl implements DzkirDetail{
    private DzkirDetailView dzkirDetailView;

    private Dzikir data;
    private com.noiztezk.db.Dzikir dataDb;
    private Person person;
    private ReadDzikir readDzikir;

    public DzkirDetailImpl(DzkirDetailView dzkirDetailView) {
        this.dzkirDetailView = dzkirDetailView;
    }

    /**
     * This is for the sake of unit testing
     * @param dzkirDetailView
     * @param data
     * @param dataDb
     * @param person
     * @param readDzikir
     */
    public DzkirDetailImpl(DzkirDetailView dzkirDetailView, Dzikir data
            , com.noiztezk.db.Dzikir dataDb, Person person
            , ReadDzikir readDzikir){
        this.dzkirDetailView = dzkirDetailView;
        this.data = data;
        this.dataDb = dataDb;
        this.person = person;
        this.readDzikir = readDzikir;
    }

    public static ReadDzikir getReadDzikir(String currentDate, Long personId, Long dzkirId) {
        return new Select().from(ReadDzikir.class)
                .where(ReadDzikir_Table.PersonId.is(personId))
                .and(ReadDzikir_Table.DzikirId.is(dzkirId))
                .and(ReadDzikir_Table.Day.is(currentDate))
                .querySingle();
    }

    @Override
    public void initData() {
        if(readDzikir.getCountByPerson() < 0){
            dzkirDetailView.setCounterText(dzkirDetailView.getViewContext().getString(R.string.free));
        }else{
            dzkirDetailView.setCounterText(readDzikir.getCountByPerson()+"");
        }
        dzkirDetailView.setArabicText(dataDb.getArabicDzikirText());
    }

    @Override
    public void handleArgument(Bundle arguments) {
        if(arguments == null)
            return;

        data = Parcels.unwrap(arguments.getParcelable(Constants.STATIC_VALUE.DATA_DZIKIR));
        dataDb = HomeActivity.fromDzikirNameDb(data.getName());
        person = Parcels.unwrap(arguments.getParcelable(Constants.STATIC_VALUE.DATA_PERSON));

        String currentDate = getDate();// get current data
        readDzikir = getReadDzikir(currentDate, person.getId(), dataDb.getId());

        insertNewDzkirForToday(currentDate);
    }

    private void insertNewDzkirForToday(String currentDate) {
        if(readDzikir == null) {
            readDzikir = new ReadDzikir();
            readDzikir.setPerson(person);
            readDzikir.setDzikir(dataDb);
            readDzikir.setDay(currentDate);
            readDzikir.setStartTime(getTime());
            readDzikir.setFinishTime(getTime());
            readDzikir.setCountByPerson(0);
            readDzikir.save();
        }
    }

    @Override
    public void handleSavedInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            data = Parcels.unwrap(savedInstanceState.getParcelable(DZKIR_DATA));
            dataDb = Parcels.unwrap(savedInstanceState.getParcelable(DZKIR_DB_DATA));
            person = Parcels.unwrap(savedInstanceState.getParcelable(PERSON_DATA));
            readDzikir = Parcels.unwrap(savedInstanceState.getParcelable(READ_DZIKIR));
        }
    }

    @Override
    public void printAllQuery() {
        List<ReadDzikir> dzikirList = new Select().from(ReadDzikir.class).queryList();
        Log.d("MNORMANSYAH", ""+dzikirList.toString());
    }

    @Override
    public void saveDataInstanceState(Bundle outState) {
        outState.putParcelable(DZKIR_DATA, Parcels.wrap(data));
        outState.putParcelable(DZKIR_DB_DATA, Parcels.wrap(dataDb));
        outState.putParcelable(PERSON_DATA, Parcels.wrap(person));
        outState.putParcelable(READ_DZIKIR, Parcels.wrap(readDzikir));
    }

    @Override
    public void incrementCount() {
        int currentCount = 1+readDzikir.getCountByPerson();
        readDzikir.setCountByPerson(currentCount);
        readDzikir.save();
        dzkirDetailView.setCounterText(Integer.toString(currentCount));
    }

    @Override
    public Dzikir getDzikir() {
        data.setCount(readDzikir.getCountByPerson()+"");
        return data;
    }
}
