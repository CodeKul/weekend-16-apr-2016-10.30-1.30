package com.codekul.telephony;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.codekul.sms.sent");
        filter.addAction("com.codekul.sms.delivered");

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(intent.getAction().equals("com.codekul.sms.sent")){
                    assignText("Message Sent");
                }
                else {
                    assignText("Message Delivered");
                }
            }
        },filter);

        sendSms();
    }

    private void sendSms(){

        Intent intentSent = new Intent("com.codekul.sms.sent");

        PendingIntent pendingIntentSent = PendingIntent
                .getBroadcast(this,
                        1234,
                        intentSent,
                        PendingIntent.FLAG_ONE_SHOT);

        Intent intentDelivered =
                new Intent("com.codekul.sms.delivered");

        PendingIntent pendingIntentDelivered = PendingIntent
                .getBroadcast(this,
                        4567,
                        intentDelivered,
                        PendingIntent.FLAG_ONE_SHOT);

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("9691331566",null,"Hello Pls make app asap",pendingIntentSent,pendingIntentDelivered);
    }

    private void getSimInfo(){

        TelephonyManager manager =
                (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        assignText("\n Imei - "+manager.getDeviceId());

        assignText("\n Operator - "+manager.getSimOperator());
        assignText("\n Operator Name - "+manager.getSimOperatorName());
    }

    private void assignText(String text){

        ((TextView)findViewById(R.id.textInfo))
                .append(text);
    }

}
