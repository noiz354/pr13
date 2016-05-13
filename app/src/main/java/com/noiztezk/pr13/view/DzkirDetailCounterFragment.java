package com.noiztezk.pr13.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.noiztezk.pr13.MainActivity2;
import com.noiztezk.pr13.R;
import com.noiztezk.pr13.db.Dzikir_Table;
import com.noiztezk.pr13.db.Person;
import com.noiztezk.pr13.db.Person_Table;
import com.noiztezk.pr13.db.ReadDzikir;
import com.noiztezk.pr13.db.ReadDzikir_Table;
import com.noiztezk.pr13.interfaces.DzkrCountModel;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.model.DzkrCount;
import com.noiztezk.pr13.utils.Constants;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Normansyah Putra on 7/26/2015.
 */
public class DzkirDetailCounterFragment extends Fragment implements DzkrCountModel {
    public static final String TAG = "DzkirDetailCounterFragment";
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIME_FORMAT = "HH:mm:ss";
    Dzikir data;
    com.noiztezk.pr13.db.Dzikir dataDb;
    public DzkrCount realdata;
    Person person;
    ReadDzikir readDzikir;

    @Bind(R.id.counterButton)
    at.markushi.ui.CircleButton counterButton;
    @Bind(R.id.btnNumNotif)
    Button mCurrent;
    @Bind(R.id.arabic)
    TextView arabic;

    public static DzkirDetailCounterFragment newInstance(Person person, Dzikir data){
        Bundle arguments = new Bundle();
        arguments.putParcelable(Constants.STATIC_VALUE.DATA_DZIKIR, Parcels.wrap(data));
        arguments.putParcelable(Constants.STATIC_VALUE.DATA_PERSON, Parcels.wrap(person));
        DzkirDetailCounterFragment fragment = new DzkirDetailCounterFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // handle fragment arguments
        Bundle arguments = getArguments();
        if(arguments != null){
            handleArguments(arguments);
        }

        handleSavedInstanceState(savedInstanceState);
        Log.d("MNORMANSYAH", "after rotate : " + realdata);
    }

    private void handleArguments(Bundle arguments)
    {
        data = Parcels.unwrap(arguments.getParcelable(Constants.STATIC_VALUE.DATA_DZIKIR));
        dataDb = MainActivity2.fromDzikirNameDb(data.getName());
        person = Parcels.unwrap(arguments.getParcelable(Constants.STATIC_VALUE.DATA_PERSON));

        Log.d("MNORMANSYAH", "received "+TAG+" : "+data);
        realdata = new DzkrCount();
        realdata.dzkrRef = data;
        data = null;// discard after save to real data

        List<ReadDzikir> dzikirList = new Select().from(ReadDzikir.class).queryList();
        Log.d("MNORMANSYAH", ""+dzikirList.toString());

        String currentDate = getDate();
        readDzikir = new Select().from(ReadDzikir.class).where(
                Person_Table.id.is(person.getId())
        ).and(Dzikir_Table.id.is(dataDb.getId()))
                .and(ReadDzikir_Table.Day.is(currentDate)).querySingle();

        if(readDzikir == null) {
            readDzikir = new ReadDzikir();
            readDzikir.setPerson(person);
            readDzikir.setDzikir(dataDb);
            readDzikir.setDay(currentDate);
            readDzikir.setStartTime(getTime());
            readDzikir.setFinishTime(getTime());
            readDzikir.setCountByPerson(0);
            readDzikir.save();
        }else{
            realdata.setCount(readDzikir.getCountByPerson());
        }


    }

    private String getDate(){
        // Parsing the date
        DateTime jodatime = new DateTime(new Date());
        // Format for output
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern(DATE_FORMAT);
        return dtfOut.print(jodatime);
    }

    private String getTime(){
        // Parsing the date
        DateTime jodatime = new DateTime(new Date());
        // Format for output
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern(TIME_FORMAT);
        return dtfOut.print(jodatime);
    }

    private void handleSavedInstanceState(Bundle savedInstanceState){
        if(savedInstanceState != null){
            realdata  = Parcels.unwrap(
                    savedInstanceState.getParcelable(
                            Constants.STATIC_VALUE.DATA_DZIKIR
                    )
            );
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View base = inflater.inflate(R.layout.dzkir_counter_layout, null);
        ButterKnife.bind(this, base);
        return base;
    }

    @Override
    public void onResume() {
        super.onResume();
        realdata.setModel(this);
        initData();
    }

    private void initData(){
        if(realdata.getCount() < 0){
            mCurrent.setText(getString(R.string.free));
        }else{
            mCurrent.setText(realdata.getCount()+"");
        }

        arabic.setText(realdata.dzkrRef.getText());
    }

    @Override
    public void onPause() {
        super.onPause();
        realdata.setModel(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MNORMANSYAH", "before rotate : " + realdata);
        outState.putParcelable(Constants.STATIC_VALUE.DATA_DZIKIR, Parcels.wrap(realdata));
    }

    @OnClick(R.id.counterButton)
    public void counterButtonClick(){
        realdata.incrementCount();
        readDzikir.setCountByPerson(realdata.getCount());
        readDzikir.save();
    }

    @Override
    public void updateUI(int result) {
        mCurrent.setText(result+"");
    }

    @Override
    public void updateUI(String result) {
        mCurrent.setText(result);
    }
}
