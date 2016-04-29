package com.noiztezk.pr13;

import android.app.SearchManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.model.Example;
import com.noiztezk.pr13.view.DzikirAdapter2;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ((PRThirteenApplication)getApplication()).getmNetComponent().inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbarMainActivity2);

        if(sharedPreferences.getString(FIRST_TIME, "").equals("")){
            sharedPreferences.edit().putString(FIRST_TIME, getString(R.string.salam)).apply();
            Snackbar.make(coordinatorLayout, getString(R.string.salam), Snackbar.LENGTH_LONG).show();
        }


        try {
            initData();
            setupRecyclerView();
        }catch (IOException ioe){
            Snackbar.make(coordinatorLayout, ioe.getMessage(), Snackbar.LENGTH_LONG).show();
        }

    }

    private void initData() throws IOException{
        InputStream is = getAssets().open(DEF_PR13_JSON);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String bufferString = new String(buffer);

        Example example = gson.fromJson(bufferString, Example.class);
        dzkrs = example.getDzkir();
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
}
