package com.codekul.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_SAMPLE = 123;
    private static final int REQUEST_ALARM = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

        findViewById(R.id.btnToast).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this,"I m toast",Toast.LENGTH_LONG);
                Button button = new Button(MainActivity.this);
                button.setText("I am toast");
                toast.setView(button);
                toast.show();
            }
        });

        findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("Content Title")
                        .setContentInfo("Content Info")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentIntent(PendingIntent
                                .getActivity(MainActivity.this,
                                        REQUEST_ALARM,
                                        new Intent(MainActivity.this,AlarmActivity.class),
                                        PendingIntent.FLAG_UPDATE_CURRENT))
                        .addAction(R.mipmap.ic_launcher,"Add",PendingIntent
                                .getActivity(MainActivity.this,
                                        REQUEST_ALARM,
                                        new Intent(MainActivity.this,AlarmActivity.class),
                                        PendingIntent.FLAG_UPDATE_CURRENT));

                Notification notification = builder.build();

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(NOTIFICATION_SAMPLE,notification);
            }
        });

        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                BlankFragment fragment = new BlankFragment();
                fragment.show(getSupportFragmentManager(),BlankFragment.KEY_DIALOG_TIMEPICKER);
            }
        });
    }
}
