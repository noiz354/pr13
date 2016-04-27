package com.noiztezk.pr13.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.noiztezk.pr13.DzkirDetailActivity;
import com.noiztezk.pr13.MainActivity;
import com.noiztezk.pr13.R;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.model.Dzkr;
import com.noiztezk.pr13.utils.Constants;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Normansyah Putra on 7/25/2015.
 */
public class DzkirAdapter extends BaseAdapter{

    // TODO determine the best data structure for searching and sorting
    List<Dzikir> dzikirs;
    MainActivity mContext;

    public DzkirAdapter(@NonNull List<Dzikir> dzkrs, @NonNull MainActivity mContext){
        this.dzikirs = dzkrs;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return dzikirs.size();
    }

    @Override
    public Object getItem(int position) {
        return dzikirs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        DzkirListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.dzkr, null);
            viewHolder = new DzkirListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (DzkirListViewHolder) v.getTag();
        }
        Dzikir item = (Dzikir) getItem(position);
        int count = Integer.parseInt(item.getCount());
        String temp = "";
        if(count < 0)
            temp += "bebas";
        else
            temp += count;
        viewHolder.count.setText(temp);
        viewHolder.text.setText(item.getText()+"");
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "MTCORSVA.ttf");
        viewHolder.text.setTypeface(tf);
        // TODO ImageButton separation
        // TODO best practice to handle onClick, right now click in arabic
        viewHolder.text.setOnClickListener(new textOnClick(position));
        return v;
    }

    class DzkirListViewHolder {
        public TextView count, text;
        public ImageButton audio;
        public RelativeLayout container;
        public DzkirListViewHolder(View base) {
            count = (TextView) base.findViewById(R.id.count);
            text = (TextView) base.findViewById(R.id.text);
            audio = (ImageButton) base.findViewById(R.id.audio);
            container =  (RelativeLayout) base.findViewById(R.id.container);
        }
    }

    class textOnClick implements View.OnClickListener{
        Dzikir data;
        int position = -1;
        public textOnClick(int position){
            this.data = (Dzikir)getItem(this.position = position);
        }

        @SuppressWarnings("NewApi")
        @Override
        public void onClick(View view) {
            Intent moveToAnotherActivity = new Intent(mContext, DzkirDetailActivity.class);
            Bundle bndlanimation =
                    ActivityOptions.makeCustomAnimation(mContext, R.anim.animation, R.anim.animation2).toBundle();
            moveToAnotherActivity.putExtra(Constants.customObject[0], Parcels.wrap(data));
            moveToAnotherActivity.putExtra(Constants.customObject[3], Parcels.wrap(dzikirs));
            moveToAnotherActivity.putExtra(Constants.customObject[4], position);
            mContext.startActivity(moveToAnotherActivity, bndlanimation);
            mContext.finish();
        }
    }
}
