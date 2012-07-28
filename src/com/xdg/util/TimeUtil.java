package com.xdg.util;

public class TimeUtil {
    public static long elapsedSecond(long start){
        return (System.currentTimeMillis()-start)/1000;
    }

}
