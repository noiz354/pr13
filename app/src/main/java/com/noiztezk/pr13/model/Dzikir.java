package com.noiztezk.pr13.model;

/**
 * Created by noiz354 on 4/27/16.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Generated("org.jsonschema2pojo")
public class Dzikir {

    @SerializedName("text")
    @Expose
    String text;
    @SerializedName("audio")
    @Expose
    String audio;
    @SerializedName("count")
    @Expose
    String count;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("read")
    @Expose
    List<String> read = new ArrayList<>();

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The audio
     */
    public String getAudio() {
        return audio;
    }

    /**
     *
     * @param audio
     * The audio
     */
    public void setAudio(String audio) {
        this.audio = audio;
    }

    /**
     *
     * @return
     * The count
     */
    public String getCount() {
        return count;
    }

    /**
     *
     * @param count
     * The count
     */
    public void setCount(String count) {
        this.count = count;
    }

}