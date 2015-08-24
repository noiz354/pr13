package com.noiztezk.pr13.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noiztezk.pr13.R;
import com.noiztezk.pr13.model.Dzkr;

/**
 * Created by SRIN on 7/27/2015.
 */
public class DzkirDetailExplainFragment extends Fragment {
    Dzkr data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dzkir_explain_layout, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static DzkirDetailExplainFragment newInstance(Dzkr data){
        return new DzkirDetailExplainFragment();
    }
}
