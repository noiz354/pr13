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
import com.noiztezk.pr13.interfaces.DzkrCountModel;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.model.DzkrCount;
import com.noiztezk.pr13.utils.Constants;

import org.parceler.Parcels;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Normansyah Putra on 7/26/2015.
 */
public class DzkirDetailCounterFragment extends Fragment implements DzkrCountModel {
    Dzikir data;
    com.noiztezk.pr13.db.Dzikir dataDb;
    public DzkrCount realdata;

    @Bind(R.id.counterButton)
    at.markushi.ui.CircleButton counterButton;
    @Bind(R.id.btnNumNotif)
    Button mCurrent;
    @Bind(R.id.arabic)
    TextView arabic;

    public static DzkirDetailCounterFragment newInstance(Class activityName, Dzikir data){
        Bundle arguments = new Bundle();
        arguments.putParcelable(Constants.customObject[1], Parcels.wrap(data));
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
        data = Parcels.unwrap(arguments.getParcelable(Constants.customObject[1]));
        dataDb = MainActivity2.fromDzikirNameDb(data.getName());
        Log.d("MNORMANSYAH", "received "+DzkirDetailCounterFragment.class.getSimpleName()+" : "+data);
        realdata = new DzkrCount();
        realdata.dzkrRef = data;
        data = null;// discard after save to real data
    }

    private void handleSavedInstanceState(Bundle savedInstanceState){
        if(savedInstanceState != null){
            realdata  = Parcels.unwrap(
                    savedInstanceState.getParcelable(
                            Constants.customFragmentRotate[0]
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
            mCurrent.setText("bebas");
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
        outState.putParcelable(Constants.customFragmentRotate[0], Parcels.wrap(realdata));
    }

    @OnClick(R.id.counterButton)
    public void counterButtonClick(){
        realdata.incrementCount();
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
