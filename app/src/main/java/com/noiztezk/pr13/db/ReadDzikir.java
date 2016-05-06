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
public class ReadDzikir extends BaseModel{

    @Column
    @PrimaryKey(
            autoincrement = true
    )
    Long id;

    @ForeignKey(references =
            {@ForeignKeyReference(columnType = Long.class,
                    columnName = "PersonId",
                    foreignKeyColumnName = "id")})
    Person person;

    @ForeignKey(references =
            {@ForeignKeyReference(columnType = Long.class,
                    columnName = "DzikirId",
                    foreignKeyColumnName = "id")})
    Dzikir dzikir;

    @Column
    long startTime;

    @Column
    long FinishTime;

    @ForeignKey(references =
            {@ForeignKeyReference(columnType = Long.class,
                    columnName = "PlaceId",
                    foreignKeyColumnName = "id")})
    Place place;
}
