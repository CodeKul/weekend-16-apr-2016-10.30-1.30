package com.codekul.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.TextView;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
            isConnected(context);
        }
        else{
            Log.i("@codekul","it is custom broadcast");
        }
    }

    private void isConnected(Context contex) {
        ConnectivityManager manager = (ConnectivityManager)
                contex.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo !=null) {
            if (networkInfo.isConnected()) {
                Log.i("@codekul","connecyed");
            } else {
                Log.i("@codekul","disconnected");
            }
        }
        else {
            Log.i("@codekul","disconnected");
        }
    }
}
