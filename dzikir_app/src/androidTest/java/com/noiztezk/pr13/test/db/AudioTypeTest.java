package com.noiztezk.pr13.test.db;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.noiztezk.db.AudioType;
import com.noiztezk.db.AudioType_Table;
import com.noiztezk.pr13.App;
import com.noiztezk.pr13.test.dagger.TestComponent;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by hafizhhabiby on 5/6/16.
 */
@RunWith(AndroidJUnit4.class)
public class AudioTypeTest {
    TestComponent component;

    @Before
    public void setUp(){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        App app
                = (App) instrumentation.getTargetContext().getApplicationContext();
        component = (TestComponent) app.getMNetComponent();
        component.inject(this);
    }

    @Test
    public void loadAudioTest(){
        AudioType audioType = new AudioType();
        audioType.setAudioExtension("mp3");
        audioType.save();

        audioType = new AudioType();
        audioType.setAudioExtension("m4a");
        audioType.save();

        AudioType mp3 = new Select().from(AudioType.class)
                .where(AudioType_Table.audioExtension.eq("mp3"))
                .querySingle();

        AudioType m4a = new Select().from(AudioType.class)
                .where(AudioType_Table.audioExtension.eq("m4a"))
                .querySingle();

        Assert.assertThat(mp3.getAudioExtension(), notNullValue());
        Assert.assertThat(m4a.getAudioExtension(), notNullValue());
        Assert.assertTrue(mp3.getAudioExtension().equals("mp3"));
        Assert.assertTrue(m4a.getAudioExtension().equals("m4a"));

    }
}
