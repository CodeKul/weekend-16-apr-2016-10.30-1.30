package com.codekul.myfirrstapp;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //generateUiViaCode();
        setContentView(R.layout.activity_main);

        Click click = new Click("hello");
        click.my();

        final Button btnOkay = (Button) findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(click);
    }

    private final class Click implements View.OnClickListener {
        private String str;

        public Click(String str){
            this.str = str;
        }

        public void my(){
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnOkay){
                //str;

                //final EditText edtName = (EditText) findViewById(R.id.edtName);
                //final TextView textView = (TextView) findViewById(R.id.textState);

                //String name = edtName.getText().toString();
                //textView.setText(name.toUpperCase());
            }
        }
    }

    private void generateUiViaCode(){

        LinearLayout.LayoutParams paramsRoot = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout layoutRoot = new LinearLayout(this);
        layoutRoot.setBackgroundColor(Color.BLACK);
        layoutRoot.setOrientation(LinearLayout.VERTICAL);
        layoutRoot.setLayoutParams(paramsRoot);

        LinearLayout.LayoutParams paramsEdit = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText edtName = new EditText(this);
        edtName.setHint("Name");
        edtName.setLayoutParams(paramsEdit);
        layoutRoot.addView(edtName);

        LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        Button btnOkay = new Button(this);
        btnOkay.setText("Okay");
        btnOkay.setLayoutParams(paramsButton);
        layoutRoot.addView(btnOkay);

        setContentView(layoutRoot);
    }
}
