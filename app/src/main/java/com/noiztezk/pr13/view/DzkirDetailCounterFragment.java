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

import com.noiztezk.pr13.R;
import com.noiztezk.db.Dzikir_Table;
import com.noiztezk.db.Person;
import com.noiztezk.db.Person_Table;
import com.noiztezk.db.ReadDzikir;
import com.noiztezk.db.ReadDzikir_Table;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.presenters.DzkirDetail;
import com.noiztezk.pr13.presenters.DzkirDetailImpl;
import com.noiztezk.pr13.presenters.DzkirDetailView;
import com.noiztezk.pr13.utils.Constants;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.parceler.Parcels;

import java.util.Date;
import java.util.List;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Normansyah Putra on 7/26/2015.
 */
public class DzkirDetailCounterFragment extends Fragment implements DzkirDetailView {
    public static final String TAG = "DzkirDetailCounterFragment";
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIME_FORMAT = "HH:mm:ss";

    DzkirDetail dzkirDetail;

    @BindView(R.id.counterButton)
    at.markushi.ui.CircleButton counterButton;
    @BindView(R.id.btnNumNotif)
    Button mCurrent;
    @BindView(R.id.arabic)
    TextView arabic;
    private Unbinder bind;

    public static DzkirDetailCounterFragment newInstance(Person person, Dzikir data){
        Bundle arguments = new Bundle();
        arguments.putParcelable(Constants.STATIC_VALUE.DATA_DZIKIR, Parcels.wrap(data));
        arguments.putParcelable(Constants.STATIC_VALUE.DATA_PERSON, Parcels.wrap(person));
        DzkirDetailCounterFragment fragment = new DzkirDetailCounterFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    public static String getDate(){
        // Parsing the date
        DateTime jodatime = new DateTime(new Date());
        // Format for output
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern(DATE_FORMAT);
        return dtfOut.print(jodatime);
    }

    public static String getTime(){
        // Parsing the date
        DateTime jodatime = new DateTime(new Date());
        // Format for output
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern(TIME_FORMAT);
        return dtfOut.print(jodatime);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View base = inflater.inflate(R.layout.dzkir_counter_layout, null);
        bind = ButterKnife.bind(this, base);
        dzkirDetail = new DzkirDetailImpl(this);
        dzkirDetail.printAllQuery();
        dzkirDetail.handleArgument(getArguments());
        dzkirDetail.handleSavedInstanceState(savedInstanceState);
        return base;
    }

    @Override
    public void onResume() {
        super.onResume();
        dzkirDetail.initData();
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public Dzikir getDzikir() {
        return dzkirDetail.getDzikir();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        dzkirDetail.saveDataInstanceState(outState);
    }

    @OnClick(R.id.counterButton)
    public void counterButtonClick(){
        dzkirDetail.incrementCount();
    }

    @Override
    public void setCounterText(String dzkirCount) {
        mCurrent.setText(dzkirCount);
    }

    @Override
    public void setArabicText(String arabicText) {
        arabic.setText(arabicText);
    }
}
