package com.noiztezk.pr13.test.db;

import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnit4;

import com.noiztezk.db.Dzikir;
import com.noiztezk.pr13.MockPRThirteenAplication;
import com.noiztezk.pr13.test.dagger.TestComponent;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by noiz354 on 8/1/16.
 */
@RunWith(AndroidJUnit4.class)
public class DzikirTest {

    TestComponent component;

    @Before
    public void setup(){
        MockPRThirteenAplication mockPRThirteenAplication = (MockPRThirteenAplication)
                getTargetContext().getApplicationContext();

        component = (TestComponent) mockPRThirteenAplication.getMNetComponent();
        component.inject(this);
    }

    @Test
    public void saveOneSingleDataDzikir(){
        Dzikir dzikir = getDummyDzikir();

        dzikir.save();

        List<Dzikir> dzikirs = new Select().from(Dzikir.class).queryList();
        assertTrue(dzikirs.size()==1);
        assertNotNull(dzikirs.get(0));
        Dzikir dzikirIndexOne = dzikirs.get(0);
        assertEquals(dzikirIndexOne, dzikir);
    }

    @Test
    public void saveMoreThanOneDztaDzikir(){
        for(int i=0;i<5;i++)
        {
            Dzikir dummyDzikir = getDummyDzikir();
            dummyDzikir.setCountDzikir(i);
            dummyDzikir.save();

        }
        List<Dzikir> dzikirs = new Select().from(Dzikir.class).queryList();
        assertTrue(dzikirs.get(0).getCountDzikir()==0);
        assertTrue(dzikirs.size()==5);
    }

    @After
    public void deleteDzikirTable(){
        Delete.tables(Dzikir.class);
    }

    @NonNull
    private Dzikir getDummyDzikir() {
        Dzikir dzikir = new Dzikir();
        dzikir.setDzikirName("doa pagi sore");
        dzikir.setArabicDzikirText("bismillahi allahi ladzi la yadurru");
        dzikir.setCountDzikir(3);
        dzikir.setReminderTime(System.currentTimeMillis());
        dzikir.setRead("pagi, siang, sore");
        return dzikir;
    }


}
