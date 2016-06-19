package com.melayer.comman;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class ConverterService extends Service {

    private MyImpl impl = new MyImpl(); // remote object

    public ConverterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Toast.makeText(this,"Bound",Toast.LENGTH_SHORT).show();

        return impl;
    }

    public class MyImpl extends IViewable.Stub {
        @Override
        public String numberConverter(int num) throws RemoteException {
            return String.valueOf(num);
        }
    }
}
