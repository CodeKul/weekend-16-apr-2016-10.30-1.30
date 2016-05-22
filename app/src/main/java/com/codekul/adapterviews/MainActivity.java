package com.codekul.adapterviews;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//http://stackoverflow.com/questions/2025282/difference-between-px-dp-dip-and-sp-on-android
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUsingArrayAdapter();
    }

    private void listUsingCustomAdapter(){

        ArrayList<MyItem> dataSet = new ArrayList<>();
        MyItem item1 = new MyItem("Android","marshmellow");
        dataSet.add(item1);

        MyItem item2 = new MyItem("iOs","ios7");
        dataSet.add(item2);

        MyItem item3 = new MyItem("windows","mango");
        dataSet.add(item3);

        MyItem item4 = new MyItem("Rim","unknown");
        dataSet.add(item4);

        MyAdapter adapter = new MyAdapter(this,dataSet);
        //ListView listMobileOs = (ListView) findViewById(R.id.listMobileOs);
        GridView gridMobileOs = (GridView) findViewById(R.id.listMobileOs);
        gridMobileOs.setAdapter(adapter);
    }

    private void listUsingArrayAdapter(){

        ArrayList<String> dataSetStr = new ArrayList<>();
        dataSetStr.add("Android");
        dataSetStr.add("iOS");
        dataSetStr.add("Symbian");
        dataSetStr.add("Windows");

        ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSetStr);

        GridView gridMobileOs = (GridView) findViewById(R.id.listMobileOs);
        gridMobileOs.setAdapter(arrAdapter);
    }

    private class MyItem /*implements Comparable<MyItem>*/{

        private String osName;
        private String osVersion;

        public MyItem(String osName, String osVersion){
            this.osName = osName;
            this.osVersion = osVersion;
        }

        public String getOsName() {
            return osName;
        }

        public void setOsName(String osName) {
            this.osName = osName;
        }

        public String getOsVersion() {
            return osVersion;
        }

        public void setOsVersion(String osVersion) {
            this.osVersion = osVersion;
        }

        /*@Override
        public int compareTo(MyItem item) {
            return 0;
        }*/
    }

    public class MyAdapter extends BaseAdapter {

        private ArrayList<MyItem> dataSet;
        private Context context;
        private LayoutInflater inflater;

        public MyAdapter(Context context, ArrayList<MyItem> dataSet){

            this.context = context;
            this.dataSet = dataSet;

            inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return dataSet.size();
        }

        @Override
        public Object getItem(int position) {
            return dataSet.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position  * 200;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //TextView textView = new TextView(context);

            View view = inflater.inflate(R.layout.adapter_item,null,false);
            MyItem item = dataSet.get(position);

            TextView textView = (TextView) view.findViewById(R.id.textAdapterItem);
            textView.setText(item.getOsName());

            TextView textVersion = (TextView) view.findViewById(R.id.textVersion);
            textVersion.setText(item.getOsVersion());

            return view;
        }
    }
}
