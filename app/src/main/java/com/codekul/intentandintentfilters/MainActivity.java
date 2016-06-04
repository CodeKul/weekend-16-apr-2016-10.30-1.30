package com.codekul.intentandintentfilters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOkay).setOnClickListener(
                new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent();
                intent.setAction("com.codekul.action.info");
                intent.addCategory("com.codekul.category.info");
                intent.setData(Uri.parse("http://www.codekul.com/android-training-institute-in-pune.html"));

                startActivity(intent);*/


                String url = "http://www.codekul.com/android-training-institute-in-pune.html";
                //Intent i = new Intent(MainActivity.this,NewsActivity.class);
                /*Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/

                Intent baseIntent = new Intent(Intent.ACTION_VIEW);
                baseIntent.setData(Uri.parse(url));

                Intent chooserIntent =
                        Intent.createChooser(baseIntent, "Select Application");

                startActivity(chooserIntent);
            }
        });
    }
}
