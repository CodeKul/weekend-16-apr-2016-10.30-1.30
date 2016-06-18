package com.melayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MyService extends Service {

    private MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            Log.i("@codekul","Path is - "+new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),"a.mp3")
                    .getPath());

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(
                    new File(Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),"a.mp3.mp3")
                            .getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
