package com.crisisera.ferraro1040;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * Created by Arcui on 02/14/2017.
 */

public class Downloader {
    private String urlPrefix = "http://iphone.tsn.ca/tsnpodcasts/tsnradio/vancouver/ferraro_";

//    public Downloader(){
//        this.current = Calendar.getInstance();
//    }

    public String getDate(int offset){
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, offset);

        String month = (current.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.CANADA)).toLowerCase();
        String day = Integer.toString(current.get(Calendar.DATE));
        String year = Integer.toString(current.get(Calendar.YEAR));
        return month+day+year;
    }

    public String getURL(int offset){
        return urlPrefix + getDate(offset) + ".mp3";
    }

    public boolean validURL(String url){
        HttpURLConnection connection;
        boolean result = false;
        try{
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(3000);
            connection.setRequestMethod("HEAD");
            result = connection.getResponseCode() == HttpURLConnection.HTTP_OK;
            System.out.println("response code for " + url + " " + connection.getResponseCode());
//            Log.d("response code: ", Integer.toString(connection.getResponseCode()));
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("connection error", e.toString());
        }
        return result;
    }

    public LinkedHashMap<String, Boolean> getRangeOfStatus(){
        return getRangeOfStatus(-1);
    }

    public LinkedHashMap<String, Boolean> getRangeOfStatus(int daysFromToday){
        int offset = daysFromToday * -1 + 1;
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();
        for (int i = offset; i <= 0; i++){
            String date        = getDate(i);
            String url         = getURL(i);
            boolean fileExists = validURL(url);

            map.put(date, fileExists);
        }
        return map;
    }


}
