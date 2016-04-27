package com.noiztezk.pr13;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.noiztezk.pr13.interfaces.MainActivityView;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.model.Example;
import com.noiztezk.pr13.presenters.MainActivityPresenter;
import com.noiztezk.pr13.presenters.MainActivityPresenterImpl;
import com.noiztezk.pr13.utils.Constants;
import com.noiztezk.pr13.view.DzkirAdapter;

import org.parceler.Parcels;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityView {
    ListView dzkrListView;
    DzkirAdapter dzkrListViewAdapter;
    Dzikir data;
    List<Dzikir> dzkrs;
    MainActivityPresenter mainActivityPresenter;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((PRThirteenApplication)getApplication()).getmNetComponent().inject(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);;
        mainActivityPresenter = new MainActivityPresenterImpl(this);
        mainActivityPresenter.setActionBarTitle(getString(R.string.main));

        setContentView(R.layout.activity_main);

        dzkrs = null;
        if(getIntent() != null){
            if(getIntent().getParcelableExtra(Constants.customObject[2]) != null){
                data = Parcels.unwrap(getIntent().getParcelableExtra(Constants.customObject[2]));
                dzkrs = Parcels.unwrap(getIntent().getParcelableExtra(Constants.customObject[5]));
            }
        }


        try {
            dzkrListView = (ListView) findViewById(R.id.dzkrListView);
            initListView();
        } catch (Exception e) {
            Log.d("MNORMANSYAH", getStackTrace(e));
        }

//        String temp = "";
//        try {
//            Log.d("MNORMANSYAH", "Hasil parsing json "+ JsonHelper.readPR13(getAssets().open("def_pr13.json")));
//        } catch (Exception e) {
//            Log.d("MNORMANSYAH", getStackTrace(e));
//        }
    }

    @Override
    public void setActionBarTitle(CharSequence text) {
        getSupportActionBar().setTitle(getString(R.string.main));
    }

    private void initListView() throws Exception {
        if(dzkrs == null) {
            InputStream is = getAssets().open("def_pr13.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String bufferString = new String(buffer);

            Example example = gson.fromJson(bufferString, Example.class);
            dzkrs = example.getDzkir();

//            dzkrs = JsonHelper.readPR13(is);
        }

        int searchIndex = -1;
        Log.d("MNORMANSYAH", "using contain MNORMANSYAH search " + (searchIndex = dzkrs.indexOf(data)));
        if(searchIndex != -1)
            dzkrs.set(searchIndex, data);
        dzkrListViewAdapter = new DzkirAdapter(dzkrs, this);
        dzkrListView.setAdapter(dzkrListViewAdapter);
        dzkrListView.addHeaderView(this.getLayoutInflater().inflate(R.layout.listview_header, null));

        dzkrListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getId() == dzkrListView.getId()) {
                    final int currentFirstVisibleItem = dzkrListView.getFirstVisiblePosition();

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().hide();
                        getSupportActionBar().hide();
                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        // getSherlockActivity().getSupportActionBar().show();
                        getSupportActionBar().show();
                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(this, "setting not configured yet", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
