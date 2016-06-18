package com.melayer.contentproviders;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loadContactsUsingArrayAdapter();

        loadContactsUsingCursorAdapter();

        //createOwnContactBase();
    }

    private void loadContactsUsingCursorAdapter(){

        final ListView listContacts =
                (ListView) findViewById(R.id.listContacts);


        listContacts.setAdapter(new
                MyAdapter(this,
                getTheCursor(),
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));
    }

    private void loadContactsUsingArrayAdapter(){

        final ListView listContacts =
                (ListView) findViewById(R.id.listContacts);

        final ArrayList<String> dataSet =
                new ArrayList<>();

       final Cursor cursor = getTheCursor();

        while(cursor.moveToNext()){

            String displayName = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String number = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            dataSet.add(displayName+"\n"+number);
        }

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSet);
        listContacts.setAdapter(adapter);
    }

    private void createOwnContactBase(){

        Cursor cursor = getTheCursor();

        DbHelper helper = getHelper();

        SQLiteDatabase sqDb = helper.getWritableDatabase();

        while (cursor.moveToNext()){

            String displayName = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String number = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            ContentValues values =
                    new ContentValues();
            values.put("contactNumber",number);
            values.put("contactName",displayName);

            Log.i("@codekul",""+sqDb.insert("myContact",null,values));
        }
        sqDb.close();
    }

    private DbHelper getHelper(){
        return  new DbHelper(this,"myContactdb",null,1);
    }

    private Cursor getTheCursor(){

        ContentResolver resolver =
                getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = {"_id",
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        Cursor cursor =
                resolver.query(uri,
                        projection,
                        selection,
                        selectionArgs,
                        sortOrder);

        return cursor;
    }

    private final class MyAdapter extends CursorAdapter {

        private LayoutInflater infalter;

        public MyAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);

            infalter = getLayoutInflater();
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            View view = infalter
                    .inflate(android.R.layout.simple_list_item_1,
                            parent,
                            false);

            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            String displayName = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String number = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            TextView textView = (TextView) view;
            textView.setText(displayName +"\n"+number);
        }
    }


}
