package com.codekul.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWrite).setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        SharedPreferences preferences =
                                getSharedPreferences("myPrefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("key_my_name","Android");
                        editor.putInt("key_my_age",7);
                        editor.putFloat("Key_my_version",6.0f);
                        editor.commit();
                    }
                });

        findViewById(R.id.btnRead).setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                        SharedPreferences preferences =
                                getSharedPreferences("myPrefs",MODE_PRIVATE);

                        String name = preferences.getString("key_my_name","def_android");
                        int age = preferences.getInt("key_my_age",-1);
                        float version = preferences.getFloat("key_my_version",-2.5f);

                        Log.i("@codekul","Name - "+name);
                        Log.i("@codekul","Age - "+age);
                        Log.i("@codekul","Version - "+version);
                    }
                });
    }
}
