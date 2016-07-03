package com.codekul.locations;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LocationManager manager =
                (LocationManager) getSystemService(LOCATION_SERVICE);

        final Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setCostAllowed(true);
        criteria.setAltitudeRequired(false);
        criteria.setSpeedRequired(true);

        manager.requestLocationUpdates(/*LocationManager.PASSIVE_PROVIDER*/ manager.getBestProvider(criteria,false), 1000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                assignText("Lat - "+location.getLatitude()+" Long - "+location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

        if (ActivityCompat
                .checkSelfPermission(this,
                        Manifest.permission
                                .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
                .PERMISSION_GRANTED) {

            ActivityCompat
                    .requestPermissions(this,
                    new String[]{Manifest.permission
                            .ACCESS_FINE_LOCATION},
                    1234 );

            return;
        }
        manager.getLastKnownLocation(String.valueOf(manager.getProvider(LocationManager.GPS_PROVIDER)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1234){

            if(resultCode == RESULT_OK){
                assignText("granted the permission");
            }
            else {
                assignText("permission rejected");
                return;
            }
        }
    }

    private void showAllProviders(final LocationManager manager){
        List<String> providers = manager.getAllProviders();

        for (String provider : providers) {
            Log.i("@codekul",provider);
            assignText(provider);
        }
    }


    private void latLongToAddress(){

        Geocoder geocoder =
                new Geocoder(this);
        try {
            List<Address> listAddress =
                    geocoder.getFromLocation(18.4929226d,73.81563d,5);

            for (Address address : listAddress) {
                Log.i("@codekul","Country - "+address.getCountryName());
                Log.i("@codekul","Postal Code - "+address.getPostalCode());
                Log.i("@codekul","Premises Code - "+address.getPremises());
                Log.i("@codekul","Address Line - "+address.getAddressLine(0));
                Log.i("@codekul","Lat - "+address.getLatitude());
                Log.i("@codekul","Longi - "+address.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addreessToLatLong(){

        Geocoder geocoder =
                new Geocoder(this);
        try {
            List<Address> listAddress =
                    geocoder.getFromLocationName("Cafe goodluck pune",5);

            for (Address address : listAddress) {
                Log.i("@codekul","Country - "+address.getCountryName());
                Log.i("@codekul","Postal Code - "+address.getPostalCode());
                Log.i("@codekul","Premises Code - "+address.getPremises());
                Log.i("@codekul","Address Line - "+address.getAddressLine(0));
                Log.i("@codekul","Lat - "+address.getLatitude());
                Log.i("@codekul","Longi - "+address.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLocations(){

    }

    private void assignText(String text){
        ((TextView)findViewById(R.id.textStatus)).append("\n"+text);
    }
}
