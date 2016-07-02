package com.codekul.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ScanResult> wifiSacnresult =
            new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WifiManager manager =
                (WifiManager) getSystemService(WIFI_SERVICE);

        /*List<WifiConfiguration> listConfigured =
                manager.getConfiguredNetworks();

        for(WifiConfiguration config : listConfigured){

            Log.i("@codekul","BSSID - "+config.BSSID);
            Log.i("@codekul","SSID - "+config.SSID);
        }*/


        /*manager.startScan();

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

              wifiSacnresult = manager.getScanResults();

                for (ScanResult scanResult : wifiSacnresult) {

                    Log.i("@codekul",scanResult.SSID);
                }
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));*/

        connectToWifi();
    }

    private void connectToWifi(){

        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", "codekul");
        wifiConfig.preSharedKey = String.format("\"%s\"", "");

        WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
//remember id
        int netId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }
}
