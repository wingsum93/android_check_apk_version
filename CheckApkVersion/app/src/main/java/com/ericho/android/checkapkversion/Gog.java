package com.ericho.android.checkapkversion;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricH on 26/11/2015.
 */
public class Gog  {
    static List<String> ignore_all = new ArrayList<>();
    static List<String> ignore_debug = new ArrayList<>();
    static List<String> ignore_verbose = new ArrayList<>();
    static List<String> ignore_important = new ArrayList<>();
    static List<String> ignore_warning = new ArrayList<>();
    static List<String> ignore_error = new ArrayList<>();

    /*in this to add ignore list */
    static {

    }
    public static void d(String tag,String text){
        if(!ignore_all.contains(tag) && !ignore_debug.contains(tag) )
            Log.d(tag, text);
    }
    public static void v(String tag,String text){
        if(!ignore_all.contains(tag) && !ignore_verbose.contains(tag) )
            Log.v(tag, text);
    }
    public static void w(String tag,String text){
        if(!ignore_all.contains(tag) && !ignore_warning.contains(tag) )
            Log.w(tag, text);
    }
    public static void i(String tag,String text){
        if(!ignore_all.contains(tag) && !ignore_important.contains(tag) )
            Log.i(tag, text);
    }
    public static void e(String tag,String text){
        if(!ignore_all.contains(tag) && !ignore_error.contains(tag) )
            Log.e(tag, text);
    }


    // less method
    public static void e(String tag,String text,Throwable ee){
        if(!ignore_all.contains(tag) && !ignore_error.contains(tag) )
            Log.e(tag, text, ee);
    }


}
