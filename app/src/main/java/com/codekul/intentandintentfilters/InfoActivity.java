package com.codekul.intentandintentfilters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import java.util.Set;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent responsibleIntent = getIntent();
        String action = responsibleIntent.getAction();
        Log.i("@codekul","Action - "+action);

//        Set<String> setCategories = responsibleIntent.getCategories();
//        for(String category : setCategories){
//            Log.i("@codekul","Category - "+category);
//        }

        Uri uri = responsibleIntent.getData();
        Log.i("@codekul","Uri - "+uri.toString());
        Log.i("@codekul","uri fragment - "+uri.getFragment());


        final WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(uri.toString());
    }
}
