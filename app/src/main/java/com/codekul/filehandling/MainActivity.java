package com.codekul.filehandling;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOpenFileOutput)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        try {
                            demoOpenFileOutput();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        findViewById(R.id.btnOpenFileInput)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        try {
                            demoOpenFileInput();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        findViewById(R.id.btnGeneralFileDemo)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        generalFileDemo();
                    }
                });

        findViewById(R.id.btnInternalFilesDir)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        try {
                            interalFileStorage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        findViewById(R.id.btnExternalFilesDir)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        try {
                            writeToAppsPrivateExternalStorage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        findViewById(R.id.btnExternalStoragePublicDir)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        try {
                            externalStoragePublicDirectory();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private void demoOpenFileOutput() throws  Exception{

        // /data/data/<your-package>/files/android.txt
        // /data/data/<your-package>/shared_prefs/myPrefs.xml

        FileOutputStream fos = openFileOutput("android.txt",MODE_APPEND);
        fos.write("Hello world".getBytes());
        fos.close();
    }

    private void demoOpenFileInput() throws Exception{

        FileInputStream fis = openFileInput("android.txt");
        //InputStreamReader isr = new InputStreamReader(fis);
        //BufferedReader reader = new BufferedReader(isr);

        StringBuilder builder = new StringBuilder();
        while(true){
            int ch = fis.read();
            if(ch == -1) break;
            else{
                builder.append((char)ch);
            }
        }
        Log.i("@codekul","Data from file - "+builder.toString());
    }

    public void generalFileDemo(){

        File []files = File.listRoots();

        files = new File("/").listFiles();

        for(File f : files){
            Log.i("@codekul","Path - "+f.getPath());

            Log.i("@codekul",f.isDirectory()
                    ? "* "+f.getName() : "- "+f.getName());;
        }
    }

    public void interalFileStorage() throws Exception{

        File file = new File(getFilesDir(),"a1.txt");

        FileOutputStream fos = new FileOutputStream(file,true);
        fos.write("Hello Again World".getBytes()); // converts string to bytes
        fos.close();

        FileInputStream fis = new FileInputStream(file);
        byte[] fileData = new byte[(int) file.length()];
        fis.read(fileData);
        fis.close();

        Log.i("@codekul",""+new String(fileData)); // bytes to string

    }

    public boolean checkExternalStorageAvailability(){

        return Environment
                .getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }

    public void writeToAppsPrivateExternalStorage() throws Exception{

        File file = new File(
                getExternalFilesDir("my"),
                "a.txt");

        if(checkExternalStorageAvailability()) {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("HEllow world".getBytes());
            fos.close();

            FileInputStream fis = new FileInputStream(file);
            byte[] fileData = new byte[(int) file.length()];
            fis.read(fileData);
            fis.close();

            Log.i("@codekul", "" + new String(fileData)); // bytes to string
        }
        else {
            Log.i("@codekul","Problem with external storage");
        }
    }

    public void externalStoragePublicDirectory() throws Exception{

        File file = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                "a.txt");

        if(checkExternalStorageAvailability()) {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("HEllow world".getBytes());
            fos.close();

            FileInputStream fis = new FileInputStream(file);
            byte[] fileData = new byte[(int) file.length()];
            fis.read(fileData);
            fis.close();

            Log.i("@codekul", "" + new String(fileData)); // bytes to string
        }
        else {
            Log.i("@codekul","Problem with external storage");
        }
    }
}
