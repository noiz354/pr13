package com.noiztezk.pr13.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by hafizhhabiby on 5/6/16.
 */
@Table(database = DzikirDatabase.class)
public class AudioType extends BaseModel {
    @Column
    @PrimaryKey(
            autoincrement = true
    )
    Long id;

    @Column
    String audioExtension;

    public String getAudioExtension() {
        return audioExtension;
    }

    public void setAudioExtension(String audioExtension) {
        this.audioExtension = audioExtension;
    }
}
