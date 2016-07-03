package com.codekul.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SensorManager manager =
                (SensorManager) getSystemService(SENSOR_SERVICE);

       useLightSensor(manager);

    }

    private void useProximitySensor(final SensorManager manager) {

        manager.registerListener(new MyListener(),
                manager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void useLightSensor(final SensorManager manager) {

        manager.registerListener(new SensorEventListener() {
                                     @Override
                                     public void onSensorChanged(SensorEvent event) {

                                         assignText(""+event.values[0]);
                                     }

                                     @Override
                                     public void onAccuracyChanged(Sensor sensor, int accuracy) {

                                     }
                                 },
                manager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void showAllSensors(final SensorManager manager) {

        List<Sensor> list =
                manager.getSensorList(Sensor.TYPE_ALL);

        final TextView textView =
                (TextView) findViewById(R.id.textStatus);

        for (Sensor sensor : list) {

            textView.append("\n" + sensor.getName());
        }
    }

    private void assignText(String text) {

        final TextView textView =
                (TextView) findViewById(R.id.textStatus);
        textView.setText(text);
    }

    private class MyListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            float[] vals = event.values;
            assignText("" + vals[0]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
