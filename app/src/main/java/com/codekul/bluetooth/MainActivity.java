package com.codekul.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_ENABLE_BT = 4565;

    private ArrayList<String> dataSetFoundDevices = new ArrayList<>();
    private ArrayAdapter<String> adapterFoundDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView listPaired = (ListView) findViewById(R.id.listPairedDevices);
        adapterFoundDevices = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSetFoundDevices);
        listPaired.setAdapter(adapterFoundDevices);

        final BluetoothAdapter adapter =
                BluetoothAdapter.getDefaultAdapter();

        if(isBluetoothAvailable(adapter)) {
            checkBluetoothEnabled(adapter);
        }

        initButtons(adapter);

        registerDeviceFoundReceiver();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_ENABLE_BT){
            if(resultCode == RESULT_OK){
                Log.i("@codekul", "Bluetooth enabled successfully");
            }
            else {
                Log.i("@codekul", "Bluetooth not enabled");
            }
        }
    }

    private Boolean isBluetoothAvailable(BluetoothAdapter adapter){

        return adapter != null;
    }

    private void checkBluetoothEnabled(BluetoothAdapter adapter){

        if(!adapter.isEnabled()){

            Intent enableBtIntent =
                    new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent,REQ_ENABLE_BT);
        }
    }

    private void getPairedDevices(BluetoothAdapter adapter){

        final ListView listPairedDevices =
                (ListView) findViewById(R.id.listPairedDevices);

        Set<BluetoothDevice> bondedDevices =
                adapter.getBondedDevices();

        ArrayList<String> dataSet =
                new ArrayList<>();

        for (BluetoothDevice device : bondedDevices) {
            dataSet.add(device.getName());
        }

        ArrayAdapter<String> adapterArr =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataSet);

        listPairedDevices.setAdapter(adapterArr);
    }

    private void scanRemoteDevices(BluetoothAdapter adapter){
        adapter.startDiscovery();
    }

    private void initButtons(final BluetoothAdapter adapter){

        findViewById(R.id.btnPaired)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPairedDevices(adapter);
                    }
               });

        findViewById(R.id.btnScan)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scanRemoteDevices(adapter);
                    }
                });

        findViewById(R.id.btnDiscoverable)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeMeDiscoverable();
                    }
                });

        findViewById(R.id.btnServer)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workAsServer(adapter);
            }
        });

        findViewById(R.id.btnClient)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workAsClient(adapter);
            }
        });
    }

    private void registerDeviceFoundReceiver(){

        IntentFilter filter =
                new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                BluetoothDevice device =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                Log.i("@codekul","name - "+device.getName() +" id "+device.getAddress());

                dataSetFoundDevices.add(device.getName());
                adapterFoundDevices.notifyDataSetChanged();
            }
        },filter);
    }

    private void makeMeDiscoverable(){

        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    private void workAsServer(final BluetoothAdapter adapter) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    BluetoothServerSocket bss =
                            adapter.listenUsingRfcommWithServiceRecord("demoapp", UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
                    BluetoothSocket bs = bss.accept();

                    bs.getOutputStream()
                            .write("Hello to bluetooth".getBytes());

                    //bs.close();
                    //bss.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void workAsClient(final BluetoothAdapter adapter){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    BluetoothDevice remoteDevice = adapter.getRemoteDevice("BC:D1:1F:07:00:02");
                    BluetoothSocket socket = remoteDevice
                            .createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
                    socket.connect();

                    InputStream is = socket.getInputStream();
                    String str = new String();
                    while(true){
                        int ch = is.read();
                        if(ch == -1) break;
                        else str += (char)ch;
                    }

                    Log.i("@codekul",""+str);
                    dataSetFoundDevices.add(str);
                    adapterFoundDevices.notifyDataSetChanged();
                    //socket.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
