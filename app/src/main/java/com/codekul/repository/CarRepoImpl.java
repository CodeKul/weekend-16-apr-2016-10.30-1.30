package com.codekul.repository;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.codekul.domain.Car;
import com.codekul.sqlite.DbHelper;

/**
 * Created by melayer on 18/6/16.
 */
public class CarRepoImpl implements CarRepository {

    private DbHelper helper ;

    public CarRepoImpl(DbHelper helper){
        this.helper = helper;
        //helper = new DbHelper()
    }

    @Override
    public void insert(Car car) throws Exception {

        SQLiteDatabase sqDb = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("carNum",car.getCarNum());
        values.put("carCountry",car.getCarName());
        values.put("carOwner",car.getCarOwner());

        sqDb.insert("carTab",null,values);

        sqDb.close();
    }

    @Override
    public void update(Car car) throws Exception {

    }

    @Override
    public void delete(Car car) throws Exception {

    }

    @Override
    public Car query(String carNum) throws Exception {
        return null;
    }
}
