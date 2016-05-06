package com.noiztezk.pr13.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.noiztezk.pr13.DzkirDetailActivity;
import com.noiztezk.pr13.MainActivity2;
import com.noiztezk.pr13.R;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.utils.Constants;

import org.joda.time.DateTime;
import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by noiz354 on 4/28/16.
 */
public class DzikirAdapter2 extends RecyclerView.Adapter<DzikirAdapter2.ViewHolder> {

    List<Dzikir> dzikirs;

    public DzikirAdapter2(List<Dzikir> dzikirs){
        this.dzikirs = dzikirs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dzkr, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(position, dzikirs.get(position));
    }

    @Override
    public int getItemCount() {
        return dzikirs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Dzikir dzikir;
        int position;

        @Bind(R.id.count)
        TextView count;
        @Bind(R.id.text)
        TextView text;
        @Bind(R.id.audio)
        ImageButton audio;
        @Bind(R.id.container)
        RelativeLayout container;
        @Bind(R.id.read)
        ImageView read;
        @Bind(R.id.name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position, Dzikir dzikir){
            this.dzikir = dzikir;
            this.position = position;

            int count = Integer.parseInt(dzikir.getCount());
            String temp = "";
            if(count < 0)
                temp += "bebas";
            else
                temp += count;

            this.count.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf"));
            this.count.setText(temp);
            this.text.setText(dzikir.getText()+"");
//            this.text.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf"));

            name.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf"));
            name.setText(dzikir.getName());

            read.setImageResource(toImageResource(dzikir.getRead()
                    , itemView.getResources().getStringArray(R.array.read)));

        }

        private int toImageResource(List<String> timeToDzikir, String[] availableDzikir){
            java.util.Date juDate = new Date();
            DateTime dt = new DateTime(juDate);
            int hour = dt.getHourOfDay();// 0-24
            if(hour>=0 && hour<12 && timeToDzikir.contains(availableDzikir[0])){
                return R.mipmap.morning;
            }else if(hour>=12 && hour <16 && timeToDzikir.contains(availableDzikir[1])){
                return R.mipmap.sunny;
            }else if(hour>=15 && hour < 19 && timeToDzikir.contains(availableDzikir[2])){
                return R.mipmap.afternoon;
            }else if(hour>=18 && timeToDzikir.contains(availableDzikir[3])){
                return R.mipmap.night;
            }else{
                throw new RuntimeException("not defined yet your time");
            }
        }

        @SuppressWarnings("NewApi")
        @OnClick(R.id.text)
        public void onClickText(){
            Context mContext = itemView.getContext();
            Intent moveToAnotherActivity = new Intent(mContext, DzkirDetailActivity.class);
            Bundle bndlanimation =
                    ActivityOptions.makeCustomAnimation(mContext, R.anim.animation, R.anim.animation2).toBundle();
            moveToAnotherActivity.putExtra(Constants.customObject[0], Parcels.wrap(dzikir));
            moveToAnotherActivity.putExtra(Constants.customObject[3], Parcels.wrap(dzikirs));
            moveToAnotherActivity.putExtra(Constants.customObject[4], position);
            mContext.startActivity(moveToAnotherActivity, bndlanimation);

            if(mContext != null && mContext instanceof MainActivity2)
                ((MainActivity2)mContext).finish();
        }
    }
}
