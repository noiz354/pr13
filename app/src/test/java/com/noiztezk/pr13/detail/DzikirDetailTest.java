package com.noiztezk.pr13.detail;

import com.noiztezk.db.Person;
import com.noiztezk.db.ReadDzikir;
import com.noiztezk.pr13.model.Dzikir;
import com.noiztezk.pr13.presenters.DzkirDetail;
import com.noiztezk.pr13.presenters.DzkirDetailImpl;
import com.noiztezk.pr13.presenters.DzkirDetailView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hafizhhabiby on 5/13/16.
 */
public class DzikirDetailTest {
    public static final int MILLION = 1_000_000;
    @Mock
    DzkirDetailView view;

    @Mock
    Dzikir dzikir;

    @Mock
    com.noiztezk.db.Dzikir dzikirDb;

    @Mock
    Person person;

    @Mock
    ReadDzikir readDzikir;

    private DzkirDetail dzkirDetail;

    public static final String DEMO_ARABIC = "ُبْحَانَ اللّهِ ، والْحَمْدُللّهِ ، وَ لا اِلهَ اِلَّا اللّهُ ، وَ اللّهُ اَكْبَرُ";

    @Before
    public void setup(){
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        dzkirDetail = new DzkirDetailImpl(view, dzikir, dzikirDb, person, readDzikir);
    }

    /**
     * test init data with larger
     */
    @Test
    public void testInitDataNormal(){
        when(readDzikir.getCountByPerson()).thenReturn(MILLION);
        when(dzikirDb.getArabicDzikirText()).thenReturn(DEMO_ARABIC);

        dzkirDetail.initData();

        verify(view).setArabicText(contains(DEMO_ARABIC));
        verify(view).setCounterText(contains(Integer.toString(MILLION)));
    }


}
