package com.example.chaapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {
    static NetworkInfo wifi,mobile;
    public static Boolean check(Context c){
        ConnectivityManager cmm=(ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        try{
            wifi =cmm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mobile= cmm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }catch (Exception e){
            e.printStackTrace();

        }if(wifi!=null && wifi.isConnected()&&wifi.isAvailable()){
            return true;
        }else if (mobile!=null&& mobile.isConnected()&& mobile.isAvailable()){
            return true;
        }else {
            return false;
        }
    }


}
