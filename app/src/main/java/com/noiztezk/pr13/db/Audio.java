package com.noiztezk.pr13.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by hafizhhabiby on 5/6/16.
 */
@Table(database = DzikirDatabase.class)
public class Audio extends BaseModel {

    @Column
    @PrimaryKey(
            autoincrement = true
    )
    Long id;

    @Column
    String path;

    @Column
    String metadata;

    @ForeignKey(references =
            {@ForeignKeyReference(columnType = Long.class,
                    columnName = "AudioId",
                    foreignKeyColumnName = "id")})
    AudioType audioType;

    @ForeignKey(references =
            {@ForeignKeyReference(columnType = Long.class,
                    columnName = "DzikirId",
                    foreignKeyColumnName = "id")})
    Dzikir dzikir;
}
