package com.example.hw20.service;


import com.example.hw20.model.Car;
import com.example.hw20.repasitory.Dao.CarRepasitory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Getter
@Setter
public class CarService {

    private int carNubmer;
    private final CarRepasitory carRepasitory;

    public CarService(CarRepasitory carRepasitory) {
        this.carRepasitory = carRepasitory;
    }

    public Car getById(int id) {
       return this.carRepasitory.findById(id);
    }

    public List<Car> getAll() {
        return carRepasitory.getAll();
    }

    public Car addCar(Car car) {
        return this.carRepasitory.save(car);
    }

    public Car addRandomCar() {
        List<String> models = List.of("BMW", "Mersedes", "Opel", "Audi", "Volkswagen");

        return Car
                .builder()
                .userIdOvner(0)
                .model(models.get(getRandomNumber(0, 5)))
                .build();
    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random()) * (max - min) + min);
    }

    public void deleteCar(int id) {
          carRepasitory.deleteCar(id);
    }

    public void updateCar (Car car, int id) {
          carRepasitory.updateCar(car, id);
    }

}