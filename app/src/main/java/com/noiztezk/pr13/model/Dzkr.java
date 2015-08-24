package com.noiztezk.pr13.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Normansyah Putra on 7/25/2015.
 */
public class Dzkr implements Parcelable{
    public String text, audio;
    public int count;

    public Dzkr(String text, String audio, int count) {
        this.text = text;
        this.audio = audio;
        this.count = count;
    }

    // Parcelling part
    public Dzkr(Parcel in){
        String[] data = new String[3];

        this.text = in.readString();
        this.audio = in.readString();
        this.count = in.readInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dzkr dzkr = (Dzkr) o;

        if (text != null ? !text.equals(dzkr.text) : dzkr.text != null) return false;
        return !(audio != null ? !audio.equals(dzkr.audio) : dzkr.audio != null);

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (audio != null ? audio.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(audio);
        dest.writeInt(count);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Dzkr createFromParcel(Parcel in) {
            return new Dzkr(in);
        }

        public Dzkr[] newArray(int size) {
            return new Dzkr[size];
        }
    };

    @Override
    public String toString() {
        return "Dzkr{" +
                "text='" + text + '\'' +
                ", audio='" + audio + '\'' +
                ", count=" + count +
                '}';
    }
}
