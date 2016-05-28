package com.codekul.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runFragmentTransaction(R.id.fragmentContainer2, nputFragment.getInstance("I love fragments"));
        runFragmentTransaction(R.id.fragmentContainer1, new BlankFragment());

        ArrayList<String> arrLst = new ArrayList<>();
    }

    private void runFragmentTransaction(int container, Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction txn = manager.beginTransaction();
        txn.replace(container,fragment);
        txn.commit();
    }
}
