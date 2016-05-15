package com.codekul.activitycom;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewsActivity extends AppCompatActivity {

    public static final  String KEY_DATA_BACK = "back";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent responsibleIntent = getIntent();
        Bundle bundle = responsibleIntent.getExtras();

        final EditText edtProcessedData = (EditText) findViewById(R.id.edtProcessedData);

        if(bundle != null){
            String someData = bundle.getString(MainActivity.KEY_SOME_DATA);
            edtProcessedData.setText((someData!=null && someData.length()>0) ? someData : "none");
        }

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String processedData = edtProcessedData.getText().toString() + System.currentTimeMillis();

                Intent intentBack = new Intent();
                intentBack.putExtra(KEY_DATA_BACK,processedData);
                setResult(RESULT_OK,intentBack);
                finish();
            }
        });
    }
}
