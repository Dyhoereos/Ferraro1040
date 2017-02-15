package com.crisisera.ferraro1040;

import java.util.Calendar;

/**
 * Created by Arcui on 02/14/2017.
 */

public class Downloader {
    private Calendar current;
    private String urlPrefix = "http://iphone.tsn.ca/tsnpodcasts/tsnradio/vancouver/ferraro_";

    public Downloader(){
        this.current = Calendar.getInstance();
    }

    public String getDate(int offset){
        current.add(Calendar.DATE, offset);

        String month = Integer.toString((current.get(Calendar.MONTH)+1));
        String day = Integer.toString(current.get(Calendar.DATE));
        String year = Integer.toString(current.get(Calendar.YEAR));
        return month+day+year;
    }
}
