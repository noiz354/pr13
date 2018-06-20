package com.noiztezk.pr13;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.tokopedia.core.drawer2.data.viewmodel.TokoPointDrawerData;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class TokoPointDrawerDataTest {
    @Test
    public void test_book_is_parcelable() {
        TokoPointDrawerData book = new TokoPointDrawerData();

        Parcel parcel = Parcel.obtain();
        book.writeToParcel(parcel, book.describeContents());
        parcel.setDataPosition(0);

        TokoPointDrawerData createdFromParcel = TokoPointDrawerData.CREATOR.createFromParcel(parcel);
        assertThat(createdFromParcel.getHasNotif(), is(0));
    }
}
