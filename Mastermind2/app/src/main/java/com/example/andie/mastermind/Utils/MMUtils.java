package com.example.andie.mastermind.Utils;

import java.text.SimpleDateFormat;

public final class MMUtils {

    public static String formatTime(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        String strTime = sdf.format(millis);
        return strTime;
    }

    public static String getTimerTxt(long timeSpent){
        int seconds = (int) (timeSpent / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        String str_min=Integer.toString(minutes);
        String str_second=Integer.toString(seconds);

        if (minutes<1) str_min="00";
        else if ((minutes>=1)&& (minutes<10))
            str_min="0"+str_min;
        if (seconds<10) str_second="0"+str_second;

        String str= str_min + ":" + str_second;

        return str;
    }
}
