package com.noiztezk.pr13.presenters;

import android.os.Bundle;

/**
 * Created by noiz354 on 5/13/16.
 */
public interface DzkirDetail extends Common{
    String DZKIR_DB_DATA = "DZKIR_DB_DATA";
    String DZKIR_DATA = "DZKIR_DATA";
    String PERSON_DATA = "PERSON_DATA";
    String READ_DZIKIR = "READ_DZIKIR";

    void initData();
    void handleArgument(Bundle arguments);
    void handleSavedInstanceState(Bundle savedInstanceState);
    void printAllQuery();
    void saveDataInstanceState(Bundle outState);
    void incrementCount();
}
