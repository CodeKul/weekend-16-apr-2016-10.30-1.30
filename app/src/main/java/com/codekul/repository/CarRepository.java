package com.codekul.repository;

import com.codekul.domain.Car;

/**
 * Created by melayer on 18/6/16.
 */
public interface CarRepository {

    void insert(Car car) throws Exception;

    void update(Car car) throws Exception;

    void delete(Car car) throws Exception;

    Car query(String carNum) throws Exception;
}
