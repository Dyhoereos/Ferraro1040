package com.crisisera.ferraro1040;

/**
 * Created by Arcui on 02/14/2017.
 */

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class URLCheckAndDownload {
    Downloader downloader;

    @Before
    public void initDownloader(){
        this.downloader = new Downloader();
    }

    /* Testing Calendar object */

    @Test
    public void validateCurrentDateEqual() {
        String month    = "2";
        String day      = "14";
        String year     = "2017";
        String expected = month+day+year;
        String actual   = downloader.getDate(0);
        assertEquals(expected, actual);
    }

    @Test
    public void validateCurrentDateDifferent() {
        String month    = "2";
        String day      = "14";
        String year     = "2017";
        String expected = month+day+year;
        String actual   = downloader.getDate(-1);
        assertNotEquals(expected, actual);
    }

    @Test
    public void validate20DaysAgo() {
        String month    = "1";
        String day      = "22";
        String year     = "2017";
        String expected = month + day + year;
        String actual   = downloader.getDate(-20);
        assertNotEquals(expected, actual);
    }

    /* Testing URL string */

    @Test
    public void validateTodaysURLString() {
        String expected = "http://iphone.tsn.ca/tsnpodcasts/tsnradio/vancouver/ferraro_feb142017.mp3";
        String actual   = downloader.getURL(0);
        assertEquals(expected, actual);
    }

    /* Test downloading header with URL */

    @Test
    public void getValidHeader(){
        String url             = downloader.getURL(0);
        boolean connectionMade = downloader.validURL(url);
        assertTrue(connectionMade);
    }

    @Test
    public void getInvalidHeader(){
        String url             = downloader.getURL(1);
        boolean connectionMade = downloader.validURL(url);
        assertFalse(connectionMade);
    }

    /* Check header for range of dates */

    @Test
    public void checkHeadersForOneWeek(){
        LinkedHashMap<String, Boolean> expected = new LinkedHashMap<>();
        expected.put("feb92017", true);
        expected.put("feb102017", true);
        expected.put("feb112017", false);
        expected.put("feb122017", false);
        expected.put("feb132017", true);
        expected.put("feb142017", true);
        expected.put("feb152017", false);

        LinkedHashMap<String, Boolean> actual = downloader.getRangeOfStatus(7);

        assertEquals(expected, actual);
    }
}
