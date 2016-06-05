package com.codekul.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DbHelper helper = new DbHelper(this,"carDb",null,1);

        findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText edCarName = (EditText) findViewById(R.id.edtCarName);
                final EditText edCarNum = (EditText) findViewById(R.id.edtCarNum);
                final EditText edCarCountry = (EditText) findViewById(R.id.edtCarCountry);

                SQLiteDatabase sqDb = helper.getWritableDatabase();
                //sqDb.execSQL("insert into carTab values('10','android','india')");

                ContentValues values = new ContentValues();
                values.put("carNum",edCarNum.getText().toString());
                values.put("carOwner",edCarName.getText().toString());
                values.put("carCountry",edCarCountry.getText().toString());

                if(sqDb.insert("carTab",null,values) != -1){
                    Log.i("@codekul","Data inserted Successfully");
                }
                else {
                    Log.i("@codekul","problem in data insertion");
                }

                sqDb.close();
            }
        });

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


            }
        });

        findViewById(R.id.btnDisplay).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText edCarNum = (EditText) findViewById(R.id.edtCarNum);

                SQLiteDatabase sqDb = helper.getReadableDatabase();

                String []columns = {"carOwner","carCountry"};
                String selection = "carNum = ?";
                String []selectionArgs = {edCarNum.getText().toString()};
                String groupBy = "carCountry";

                Cursor cursor =
                        sqDb.query("carTab",
                                columns,
                                selection,
                                selectionArgs,
                                groupBy,null,null);

                while(cursor.moveToNext()){

                    String carNum = cursor.getString(0);
                    String ownerName = cursor.getString(1);
                    String carCountry = cursor.getString(cursor.getColumnIndex("carCountry"));

                    Log.i("@codekul","Car Num - "+carNum+" Owner - "+ownerName+" Country - "+carCountry);
                }

                sqDb.close();
            }
        });
    }
}
