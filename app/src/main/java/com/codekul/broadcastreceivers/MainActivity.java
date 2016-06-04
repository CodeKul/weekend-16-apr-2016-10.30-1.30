package com.codekul.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.codekul.action.custom");
                sendBroadcast(intent);
            }
        });

        /*IntentFilter filterNetwork = new IntentFilter();
        filterNetwork.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceicer,filterNetwork);*/
    }

    public final BroadcastReceiver myReceicer = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            isConnected(context);
        }

        private void isConnected(Context contex) {
            ConnectivityManager manager = (ConnectivityManager)
                    contex.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo !=null) {
                if (networkInfo.isConnected()) {
                    ((TextView) findViewById(R.id.btnSend)).setText("Connected");
                } else {
                     ((TextView) findViewById(R.id.btnSend)).setText("Disconnected");
                }
            }
            else {
                ((TextView) findViewById(R.id.btnSend)).setText("Disconnected");
            }
        }
    };
}
