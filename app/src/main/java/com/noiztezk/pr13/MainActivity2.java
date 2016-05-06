package com.noiztezk.pr13;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.noiztezk.pr13.db.AudioType;
import com.noiztezk.pr13.db.AudioType_Table;
import com.noiztezk.pr13.db.Person;
import com.noiztezk.pr13.db.Person_Table;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.model.Example;
import com.noiztezk.pr13.view.DzikirAdapter2;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by noiz354 on 4/28/16.
 */
public class MainActivity2 extends AppCompatActivity {

    public static final String FIRST_TIME = "first_time";
    public static final String DEF_PR13_JSON = "def_pr13.json";
    @Bind(R.id.coor_layout_main_activity2)
    CoordinatorLayout coordinatorLayout;

    @Bind(R.id.toolbar_main_activity2)
    Toolbar toolbarMainActivity2;

    @Bind(R.id.appbar_main_activity2)
    AppBarLayout appBarMainActivity2;

    @Bind(R.id.recylerview_main_activity2)
    RecyclerView recyclerViewMainActivity2;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Gson gson;

    List<Dzikir> dzkrs;

    Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ((PRThirteenApplication)getApplication()).getmNetComponent().inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarMainActivity2);

        String[] unidentifiedPerson = getResources().getStringArray(R.array.unknown_user);
        String[] audioFormat = getResources().getStringArray(R.array.audio_format);

        if(sharedPreferences.getString(FIRST_TIME, "").equals("")){
            sharedPreferences.edit().putString(FIRST_TIME, getString(R.string.salam)).apply();
            insertKnownAudioType(audioFormat);
            insertUnknownUser(unidentifiedPerson);
            Snackbar.make(coordinatorLayout, getString(R.string.salam), Snackbar.LENGTH_LONG).show();
        }else{
            person = new Select().from(Person.class).where(
                    Condition.column(Person_Table.name.getNameAlias())
                            .eq(getUnIdentifiedPerson(unidentifiedPerson).getName()))
                    .querySingle();
        }

        try {
            initData();
            saveDzikirJsonToDb(dzkrs);
            setupRecyclerView();
        }catch (IOException ioe){
            Snackbar.make(coordinatorLayout, ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }

    }

    public static List<Dzikir> getData(Context context, Gson gson) throws IOException{
        InputStream is = context.getAssets().open(DEF_PR13_JSON);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String bufferString = new String(buffer);

        Example example = gson.fromJson(bufferString, Example.class);
        return example.getDzkir();
    }

    private void initData() throws IOException{
        dzkrs = getData(this, gson);
    }

    private void setupRecyclerView(){
        recyclerViewMainActivity2.setLayoutManager(new LinearLayoutManager(recyclerViewMainActivity2.getContext()));
        recyclerViewMainActivity2.setAdapter(new DzikirAdapter2(dzkrs));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(coordinatorLayout, "setting not configured yet", Snackbar.LENGTH_LONG)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertKnownAudioType(String[] audioFormat){
        for(String audioKnownType : audioFormat){
            AudioType audioType = new AudioType();
            audioType.setAudioExtension(audioKnownType);
            audioType.save();
        }
    }

    private Person getUnIdentifiedPerson(String[] unidentifiedUser){
        Person person = new Person();
        final int name = 0, age = 1, email = 2, deviceId= 3;
        for (String findHere : unidentifiedUser){
            String[] parseString = findHere.split("_");
            switch (Integer.parseInt(parseString[0])){
                case name:
                    person.setName(parseString[1]);
                    break;
                case age:
                    person.setAge(parseString[1]);
                    break;
                case email:
                    person.setEmail(parseString[1]);
                    break;
                case deviceId:
                    person.setDeviceId(parseString[1]);
                    break;
            }
        }
        person.setLoginTimeStamp(System.currentTimeMillis());
        return person;
    }

    private void insertUnknownUser(String[] unidentifiedUser){
        person = getUnIdentifiedPerson(unidentifiedUser);
        person.save();
    }

    private synchronized void saveDzikirJsonToDb(List<Dzikir> dzikirs){
        for ( Dzikir dzikir : dzikirs ){
            com.noiztezk.pr13.db.Dzikir dz
                    = new com.noiztezk.pr13.db.Dzikir();
            dz.setDzikirName(dzikir.getName());
            dz.setArabicDzikirText(dzikir.getText());
            dz.setCountDzikir(Integer.parseInt(dzikir.getCount()));
            dz.save();
        }
    }
}
