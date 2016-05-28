package com.codekul.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mt("onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        mt("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();

        mt("onStart");
        ;
        ;;;;;;;;;;

    }

    @Override
    protected void onResume() {
        super.onResume();

        mt("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        mt("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mt("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mt("onDestroy");
    }

    private void mt(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
