package com.noiztezk.pr13;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.utils.Constants;
import com.noiztezk.pr13.view.DzkirDetailCounterFragment;
import com.noiztezk.pr13.view.DzkirPagerAdapter;

import org.parceler.Parcels;

import java.util.List;

public class DzkirDetailActivity extends AppCompatActivity {
    Dzikir data;
    List<Dzikir> datas;
    Switch switch_to;
    TextView counter, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_dzkir_detail);
        switch_to = (Switch)findViewById(R.id.switch_to);
        switch_to.setOnCheckedChangeListener(new switchCheckedLis());
        counter = (TextView)findViewById(R.id.counter);
        detail = (TextView)findViewById(R.id.detail);
    }

    private void grapIntent(){
        Intent intent = getIntent();
        data = Parcels.unwrap(intent.getParcelableExtra(Constants.customObject[0]));
        datas = Parcels.unwrap(intent.getParcelableExtra(Constants.customObject[3]));
        Log.d("MNORMANSYAH", DzkirDetailActivity.class.getSimpleName()+" data received " +data.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        grapIntent();
        // TODO fix this FragmentFactory.getInstance().newInstance(DzkirDetailCounterFragment.class, this, null)
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.customFragmentTag[0]);
        if(fragment != null && fragment instanceof DzkirDetailCounterFragment){

        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.dzkirDetailCounterFragment, DzkirDetailCounterFragment.newInstance(DzkirDetailActivity.class, data), Constants.customFragmentTag[0])
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dzkir_detail, menu);
        return true;
    }

    @SuppressWarnings("NewApi")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        data = ((DzkirDetailCounterFragment)getSupportFragmentManager().findFragmentByTag(Constants.customFragmentTag[0])).realdata.mergeWithRef();
        Intent moveToOtherActivity = new Intent(this, MainActivity.class);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(this, R.anim.animation, R.anim.animation2).toBundle();
        moveToOtherActivity.putExtra(Constants.customObject[2], Parcels.wrap(data));

        int searchIndex = -1;
        Log.d("MNORMANSYAH", "using contain MNORMANSYAH search " + (searchIndex = datas.indexOf(data)));
        if(searchIndex != -1)
            datas.set(searchIndex, data);
        moveToOtherActivity.putExtra(Constants.customObject[5], Parcels.wrap(datas));
        startActivity(moveToOtherActivity, bndlanimation);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class switchCheckedLis implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                detail.setVisibility(View.VISIBLE);
                counter.setVisibility(View.GONE);
                ViewPager pager = (ViewPager)DzkirDetailActivity.this.findViewById(R.id.dzkir_explain_view_page);
                pager.setVisibility(View.VISIBLE);
                FragmentPagerAdapter adapter = new DzkirPagerAdapter(getSupportFragmentManager());
                pager.setAdapter(adapter);
                DzkirDetailActivity.this.findViewById(R.id.dzkirDetailCounterFragment).setVisibility(View.GONE);
            }else{
                detail.setVisibility(View.GONE);
                counter.setVisibility(View.VISIBLE);
                ViewPager pager = (ViewPager)DzkirDetailActivity.this.findViewById(R.id.dzkir_explain_view_page);
                pager.setVisibility(View.GONE);
                DzkirDetailActivity.this.findViewById(R.id.dzkirDetailCounterFragment).setVisibility(View.VISIBLE);
            }
        }
    }
}
