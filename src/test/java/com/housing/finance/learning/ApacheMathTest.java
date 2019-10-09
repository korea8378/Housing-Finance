package com.housing.finance.learning;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.junit.Test;

public class ApacheMathTest {

    @Test
    public void testSimpleRegression() {
        SimpleRegression simpleRegression = new SimpleRegression(true);

        simpleRegression.addData(2005, 864);
        simpleRegression.addData(2006, 416);
        simpleRegression.addData(2007, 263);
        simpleRegression.addData(2008, 1659);
        simpleRegression.addData(2009, 394);
        simpleRegression.addData(2010, 2233);
        simpleRegression.addData(2011, 1140);
        simpleRegression.addData(2012, 2527);
        simpleRegression.addData(2013, 3486);
        simpleRegression.addData(2014, 2932);
        simpleRegression.addData(2015, 3906);
        simpleRegression.addData(2016, 5073);
        simpleRegression.addData(2017, 3278);

        System.out.println("2018 = " + simpleRegression.predict(2018));
    }
}
