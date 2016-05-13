package com.noiztezk.db;

import com.noiztezk.db.DzikirDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

/**
 * Created by hafizhhabiby on 5/5/16.
 */
@Table(database = DzikirDatabase.class)
@Parcel(analyze={Place.class})
public class Place extends BaseModel {
    @Column
    @PrimaryKey(
            autoincrement = true
    )
    Long id;

    @Column
    String placeDescription;

    @Column
    long longitude;

    @Column
    long latitude;

    @ForeignKey(references =
            {@ForeignKeyReference(columnType = Long.class,
                    columnName = "DzikirId",
                    foreignKeyColumnName = "id")})
    Dzikir dzikir;
}
