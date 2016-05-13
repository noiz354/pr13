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
 * Created by hafizhhabiby on 5/6/16.
 */
@Table(database = DzikirDatabase.class)
@Parcel(analyze={ReadDzikir.class})
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
    String startTime;

    @Column
    String FinishTime;

    @Column
    String Day;

    @Column
    int countByPerson;

    @ForeignKey(references =
            {@ForeignKeyReference(columnType = Long.class,
                    columnName = "PlaceId",
                    foreignKeyColumnName = "id")})
    Place place;

    public int getCountByPerson() {
        return countByPerson;
    }

    public void setCountByPerson(int countByPerson) {
        this.countByPerson = countByPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Dzikir getDzikir() {
        return dzikir;
    }

    public void setDzikir(Dzikir dzikir) {
        this.dzikir = dzikir;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
