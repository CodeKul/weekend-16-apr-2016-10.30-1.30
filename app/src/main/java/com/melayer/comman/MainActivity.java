package com.melayer.comman;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IViewable viewable;

    private ServiceConnection conn =
            new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            viewable =
                    IViewable.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            viewable = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clicked(View v){

        if(v.getId() == R.id.btnBind){

            Intent intent =
                    new Intent("com.codekul.aidl.service");

            bindService(convertImplicitIntentToExplicitIntent(intent),
                    conn,
                    BIND_AUTO_CREATE);
        }
        else {
            try {
                String num = viewable.numberConverter(50);
                Log.i("@codekul",num);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public Intent convertImplicitIntentToExplicitIntent(Intent implicitIntent) {
        PackageManager pm = getPackageManager();
        List<ResolveInfo> resolveInfoList = pm.queryIntentServices(implicitIntent, 0);

        if (resolveInfoList == null || resolveInfoList.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfoList.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
