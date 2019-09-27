package com.fg.mvp_collection_demo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

public class NetworkUtil {
    public static int NET_CNNT_BAIDU_OK=1;//NetworkAvailable
    public static int NET_CNNT_BAIDU_TIMEOUT=2;//
    public static int NET_NOT_PERPARE=3;//Net no ready
    public static int NET_ERROR=4;//Net error
    public static int TIMEOUT=3000;//TIMEOUT

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager manager=(ConnectivityManager)context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(null==manager)
            return false;
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(null==info||!info.isAvailable())
            return false;
        return true;
    }

    /**
     * getLocallpAddress
     * @return
     */
    public static String getLocallpAddress(){
        String ret="";
        try {
            for(Enumeration<NetworkInterface> enumeration=NetworkInterface.getNetworkInterfaces();enumeration.hasMoreElements();)
            {
                NetworkInterface intf=enumeration.nextElement();
                for(Enumeration<InetAddress> enumlpAddr=intf.getInetAddresses();enumlpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress=enumlpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress())
                    {
                        ret=inetAddress.getHostAddress().toString();
                    }
                }
            }
        }catch (SocketException e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 返回当前网络状态
     * @param context
     * @return
     */
    public static int getNetState(Context context)
    {
        try{
            ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivityManager!=null)
            {
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null)
                {
                    if(networkInfo.isConnected()&&networkInfo.isAvailable())
                    {
                        if(!connectionNetWork())
                        {
                            return NET_CNNT_BAIDU_TIMEOUT;
                        }else {
                            return NET_CNNT_BAIDU_OK;
                        }
                    }else {
                        return NET_NOT_PERPARE;
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return NET_ERROR;
    }

    /**
     * ping "https://www.baidu.com/"
     * @return
     */
    static private boolean connectionNetWork(){
        boolean result=false;
        HttpURLConnection httpURLConnection=null;
        try{
            httpURLConnection=(HttpURLConnection)new URL("https://www.baidu.com/").openConnection();
            httpURLConnection.setConnectTimeout(TIMEOUT);
            httpURLConnection.connect();
            result= true;
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if(null!=httpURLConnection)
            {
                httpURLConnection.disconnect();
            }
            httpURLConnection=null;
        }
        return result;
    }

    /**
     * check is 3G
     * @param context
     * @return
     */
    public static boolean is3G(Context context)
    {
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo=manager.getActiveNetworkInfo();
        if(activeNetInfo!=null&&activeNetInfo.getType()==manager.TYPE_MOBILE)
        {
            return true;
        }
        return false;
    }

    /**
     * check Wifi
     * @param context
     * @return
     */
    public static boolean isWifi(Context context)
    {
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&info.getType()==ConnectivityManager.TYPE_WIFI)
        {
            return true;
        }
        return false;
    }

    /**
     * check 2G
     * @param context
     * @return
     */
    public static boolean is2G(Context context){
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&(info.getSubtype()== TelephonyManager.NETWORK_TYPE_EDGE||info.getSubtype()==TelephonyManager.NETWORK_TYPE_GPRS||info.getSubtype()==TelephonyManager.NETWORK_TYPE_CDMA))
        {
            return true;
        }
        return false;
    }

    /**
     * check wifi
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(Context context)
    {
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((manager.getActiveNetworkInfo()!=null&&manager.getActiveNetworkInfo().getState()==NetworkInfo.State.CONNECTED)||mgrTel.getNetworkType()==TelephonyManager.NETWORK_TYPE_UMTS);
    }
}
