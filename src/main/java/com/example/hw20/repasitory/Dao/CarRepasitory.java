package com.example.hw20.repasitory.Dao;


import com.example.hw20.model.Car;

import java.util.List;

public interface CarRepasitory {
    Car findById(int id);

    List<Car> getAll();

    Car save(Car car);

    void updateCar(Car car, int id);

    void deleteCar(int id);

}
