package com.codekul.activitycom;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Method;

public class MainActivity extends Activity {

    public static final String KEY_SOME_DATA = "key_some_datasdhdfhj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText edtData = (EditText) findViewById(R.id.edtData);

                Intent intent = new Intent(MainActivity.this,NewsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(KEY_SOME_DATA,edtData.getText().toString());

                intent.putExtras(bundle);

               //startActivity(intent);

                startActivityForResult(intent,5468);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 5468){
            if(resultCode == RESULT_OK){

                EditText edtData = (EditText) findViewById(R.id.edtData);
                Bundle bundle = data.getExtras();

                String processedData = bundle.getString(NewsActivity.KEY_DATA_BACK);
                edtData.setText(processedData);
            }
        }
    }
}
