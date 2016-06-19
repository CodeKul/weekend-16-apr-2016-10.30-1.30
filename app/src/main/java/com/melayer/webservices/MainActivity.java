package com.melayer.webservices;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnGet)
                .setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        new WebTask().execute("http://jsonplaceholder.typicode.com/posts");
                    }
                });
    }

    private void startTask(){
        new MyTask()
                .execute(/*new String[]{"10","20","30"}*//*params*/);
    }

    private void uiWithoutWorkerThread(){
        Button btnGet = (Button)
                findViewById(R.id.btnGet);

        for(int i = 0 ; i < 10 ;i ++){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            btnGet.setText(""+i);
        }
    }

    private void uiWithWokerThread(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                uiWithoutWorkerThread();
            }
        }).start();
    }

    private String connectAndFecthComments(String urlWeb) throws Exception{

        // u can use libraries i.e. Volley, okhttp3

        // 2xx - request reached to server and came back successfully
        // 3xx - redirection for many aspects e.g authorization \
        // 4xx  - bad request or related to errors in request
        // 5xx - internal server error

        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(urlWeb);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            int resCode = connection.getResponseCode();

            Log.i("@codekul","Response code - "+resCode);

            InputStream is =
                    connection.getInputStream();

            while(true){

                int ch = is.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }

            Log.i("@codekul",""+builder.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    private ArrayList<String> createCommentList(String json) throws Exception{

        // u can use libraries here like Jackson - java obj to json, Gson


        JSONArray arrComments = new JSONArray(json);
        ArrayList<String> dataSet = new ArrayList<>();

        for(int i = 0 ; i < arrComments.length() ;i++) {

            JSONObject obj = arrComments.getJSONObject(i);
            dataSet.add(obj.getString("title"));
        }

        return  dataSet;
    }

    private class MyTask extends
            AsyncTask<String/*params*/, Integer/*progress*/,String/*result*/>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // ui thread

            Log.i("@codekul","before processing");
        }

        @Override
        protected String doInBackground(String... params/*from execute method*/) {
            // worker thread

            for(int i = 0; i < 10 ; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(new Integer[]{i}/*progress*/);
            }
            return "success" /*Result*/;
        }

        @Override
        protected void onPostExecute(String s /*from doInBackground method*/) {
            super.onPostExecute(s);

            Log.i("@codekul","Task executed "+s);
            //ui thread
        }

        @Override
        protected void onProgressUpdate(Integer... values/*from publishProgress*/) {
            super.onProgressUpdate(values);
            //ui thread
            Button btnGet = (Button)
                    findViewById(R.id.btnGet);
            btnGet.setText(""+values[0]);
        }
    }

    private class WebTask extends AsyncTask<String, Void, ArrayList<String>>{

        private ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = ProgressDialog
                    .show(MainActivity.this,"Web","Fetching comments");
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            String json = "";
            ArrayList<String> dataSet = new ArrayList<>();
            try {

                json = connectAndFecthComments(params[0]);
                dataSet = createCommentList(json);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return dataSet;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);

            pd.dismiss();

            ListView listComments =
                    (ListView) findViewById(R.id.listComments);

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1,
                    s);

            listComments.setAdapter(adapter);
        }
    }
}
