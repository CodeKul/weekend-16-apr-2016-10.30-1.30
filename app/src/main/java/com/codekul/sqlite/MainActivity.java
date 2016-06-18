package com.codekul.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codekul.domain.Car;
import com.codekul.repository.CarRepoImpl;
import com.codekul.repository.CarRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DbHelper helper = new DbHelper(this,"carDb",null,1);
        final CarRepository repository = new CarRepoImpl(helper);

        findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText edCarName = (EditText) findViewById(R.id.edtCarName);
                final EditText edCarNum = (EditText) findViewById(R.id.edtCarNum);
                final EditText edCarCountry = (EditText) findViewById(R.id.edtCarCountry);

                Car car = new Car();
                car.setCarName(edCarName.getText().toString());
                car.setCarNum(edCarNum.getText().toString());
                car.setCarOwner(edCarCountry.getText().toString());

                try {
                    repository.insert(car);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                final EditText edCarName = (EditText) findViewById(R.id.edtCarName);
                final EditText edCarNum = (EditText) findViewById(R.id.edtCarNum);
                final EditText edCarCountry = (EditText) findViewById(R.id.edtCarCountry);

                SQLiteDatabase sqDb =
                        helper.getWritableDatabase();

                String table = "carTab";
                ContentValues values = new ContentValues();
                values.put("carName",edCarName.getText().toString());
                values.put("carCountry",edCarCountry.getText().toString());

                String whereClause = "carNum = ?";
                String[] whereArgs = {edCarNum.getText().toString()};

                sqDb.update(table,values,whereClause,whereArgs);

                sqDb.close();
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final EditText edCarNum = (EditText)
                        findViewById(R.id.edtCarNum);

               SQLiteDatabase sqDb = helper.getWritableDatabase();

                String table = "carTab";
                String whereClause = "carNum = ?";
                String[] whereArgs = {edCarNum.getText().toString()};

                sqDb.delete(table,whereClause,whereArgs);

                sqDb.close();
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
