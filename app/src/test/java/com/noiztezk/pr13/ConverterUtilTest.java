package com.noiztezk.pr13;

import com.noiztezk.pr13.utils.UnitTest;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by Normansyah Putra on 7/25/2015.
 *
 * http://www.vogella.com/tutorials/AndroidTesting/article.html
 *
 */
public class ConverterUtilTest {
    @Test
    public void testConvertFahrenheitToCelsius() {
        float actual = UnitTest.add(90, 190.89f);
        // expected value is 212
        float expected = 280;
        // use this method because float is not precise
        assertEquals("Conversion from celsius to fahrenheit failed", expected,
                actual, 0.001);
    }

}
