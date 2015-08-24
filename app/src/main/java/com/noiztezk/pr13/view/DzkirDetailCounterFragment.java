package com.noiztezk.pr13.view;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.noiztezk.pr13.R;
import com.noiztezk.pr13.interfaces.DzkrCountModel;
import com.noiztezk.pr13.model.Dzkr;
import com.noiztezk.pr13.model.DzkrCount;
import com.noiztezk.pr13.utils.Constants;

import org.w3c.dom.Text;

import at.markushi.ui.CircleButton;

/**
 * Created by Normansyah Putra on 7/26/2015.
 */
public class DzkirDetailCounterFragment extends Fragment implements DzkrCountModel {
    Dzkr data;
    public DzkrCount realdata;
    at.markushi.ui.CircleButton counterButton;
    Button mCurrent;
    TextView arabic;

    public static DzkirDetailCounterFragment newInstance(Class activityName, Dzkr data){
        Bundle arguments = new Bundle();
        arguments.putParcelable(Constants.customObject[1], data);
        DzkirDetailCounterFragment fragment = new DzkirDetailCounterFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // handle fragment arguments
        Bundle arguments = getArguments();
        if(arguments != null)
        {
            handleArguments(arguments);
        }

        handleSavedInstanceState(savedInstanceState);
        Log.d("MNORMANSYAH", "after rotate : " + realdata);
    }

    private void handleArguments(Bundle arguments)
    {
        data = (Dzkr) arguments.getParcelable(Constants.customObject[1]);
        Log.d("MNORMANSYAH", "received "+DzkirDetailCounterFragment.class.getSimpleName()+" : "+data);
        realdata = new DzkrCount();
        realdata.dzkrRef = data;
        data = null;// discard after save to real data
    }

    private void handleSavedInstanceState(Bundle savedInstanceState){
        if(savedInstanceState != null){
            realdata  = savedInstanceState.getParcelable(Constants.customFragmentRotate[0]);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // save activity here
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View base = inflater.inflate(R.layout.dzkir_counter_layout, null);
        initUI(base);
        return base;
    }

    private void initUI(@NonNull View base){
        counterButton = (CircleButton) base.findViewById(R.id.counterButton);
        counterButton.setOnClickListener(new CounterButtonOnClick());
        mCurrent = (Button)base.findViewById(R.id.btnNumNotif);
        realdata.setModel(this);
        arabic = (TextView) base.findViewById(R.id.arabic);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData(){
        if(realdata.getCount() < 0){
            mCurrent.setText("bebas");
        }else{
            mCurrent.setText(realdata.getCount()+"");
        }

        arabic.setText(realdata.dzkrRef.text);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MNORMANSYAH", "before rotate : " + realdata);
        outState.putParcelable(Constants.customFragmentRotate[0], realdata);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // destroy activity here
    }

    private class CounterButtonOnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            realdata.incrementCount();
        }
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
