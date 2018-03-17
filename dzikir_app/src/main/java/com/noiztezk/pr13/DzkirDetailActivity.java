package com.noiztezk.pr13;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.noiztezk.db.Person;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.utils.Constants;
import com.noiztezk.pr13.view.DzkirDetailCounterFragment;
import com.noiztezk.pr13.view.DzkirPagerAdapter;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.Unbinder;

public class DzkirDetailActivity extends AppCompatActivity {

    Dzikir data;

    Person person;

    @BindView(R.id.switch_to)
    Switch switch_to;
    @BindView(R.id.counter)
    TextView counter;
    @BindView(R.id.detail)
    TextView detail;

    Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dzkir_detail);
        bind = ButterKnife.bind(this);
        grabIntent();

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(DzkirDetailCounterFragment.TAG);
        if(fragment != null && fragment instanceof DzkirDetailCounterFragment){

        }else {
            DzkirDetailCounterFragment detailCounterFragment = DzkirDetailCounterFragment.newInstance(person, data);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.dzkirDetailCounterFragment, detailCounterFragment, DzkirDetailCounterFragment.TAG)
                    .commit();
        }
    }

    private void grabIntent(){
        if (getIntent() == null)
            return;

        Intent intent = getIntent();
        data = Parcels.unwrap(intent.getParcelableExtra(Constants.STATIC_VALUE.DATA_DZIKIR));
        person = Parcels.unwrap(intent.getParcelableExtra(Constants.STATIC_VALUE.DATA_PERSON));
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
        DzkirDetailCounterFragment fragment = (DzkirDetailCounterFragment)getSupportFragmentManager().findFragmentByTag(DzkirDetailCounterFragment.TAG);
        data = fragment.getDzikir();
        Intent moveToOtherActivity = new Intent(this, HomeActivity.class);
        Bundle bndlanimation =
                ActivityOptions.makeCustomAnimation(this, R.anim.animation, R.anim.animation2).toBundle();
        moveToOtherActivity.putExtra(Constants.STATIC_VALUE.DATA_DZIKIR, Parcels.wrap(data));

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

    @OnCheckedChanged(R.id.switch_to)
    public void switchCheckedList(CompoundButton compoundButton, boolean b){
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
