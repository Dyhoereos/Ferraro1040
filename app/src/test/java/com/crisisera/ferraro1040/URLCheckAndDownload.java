package com.crisisera.ferraro1040;

/**
 * Created by Arcui on 02/14/2017.
 */

import android.util.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;
import java.util.Calendar;

import static org.junit.Assert.*;
import static org.junit.rules.ExpectedException.*;

public class URLCheckAndDownload {
    Downloader downloader;

//    @Rule
//    public final ExpectedException exception = none();

    @Before
    public void initDownloader(){
        this.downloader = new Downloader();
    }

//    /* Testing Calendar object */

    @Test
    public void validateCurrentDateEqual() {
        String month = "2";
        String day = "14";
        String year = "2017";
        String expected = month+day+year;
        String actual = downloader.getDate(0);
        assertEquals(expected, actual);
    }

    @Test
    public void validateCurrentDateDifferent() {
        String month = "2";
        String day = "14";
        String year = "2017";
        String expected = month+day+year;
        String actual = downloader.getDate(-1);
        assertNotEquals(expected, actual);
    }

    @Test
    public void validate20DaysAgo() {
        String month = "1";
        String day = "22";
        String year = "2017";
        String expected = month + day + year;
        String actual = downloader.getDate(-20);
        assertNotEquals(expected, actual);
    }
}
