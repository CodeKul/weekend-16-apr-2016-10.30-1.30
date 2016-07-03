package com.codekul.locations;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addreessToLatLong();
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
}
