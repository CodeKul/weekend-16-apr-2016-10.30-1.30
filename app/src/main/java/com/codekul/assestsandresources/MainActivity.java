package com.codekul.assestsandresources;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mt("created");



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int pureWhite = getColor(R.color.pureWhite);

        }
        else {
            int pureWhite = getResources().getColor(R.color.pureWhite); // depcicated
            pureWhite = ContextCompat.getColor(this,R.color.pureWhite);
        }

        Button btnokay=(Button) findViewById(R.id.btnOkay);
        btnokay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = (EditText)findViewById(R.id.edtuserName);
                EditText password = (EditText)findViewById(R.id.edtPassword);
                userName.setBackgroundColor(Color.GREEN);
                userName.setTextColor(Color.BLACK);
                password.setBackgroundColor(Color.BLUE);
            }
        });
    }

    @Override
    protected void onRestart() {

        mt("on Restart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mt("destroyed");
    }

    private void mt(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

   /* @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mt("onsavedinsance state");

        outState.putString("key_date", ((TextView)findViewById(R.id.textState)).getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mt("on restore instance state");

        if(savedInstanceState != null){

            ((TextView)findViewById(R.id.textState)).setText(
                    savedInstanceState.getString("key_date","none"));
        }
        else{
            ((TextView)findViewById(R.id.textState)).setText(""+new Date());
        }
    }*/

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

            getWindow().setBackgroundDrawableResource(R.drawable.jimone);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            getWindow().setBackgroundDrawableResource(R.drawable.jimtwo);
        }
        else {
            getWindow().setBackgroundDrawableResource(R.mipmap.ic_launcher);
        }
    }
}
