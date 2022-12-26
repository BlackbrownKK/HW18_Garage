package com.example.hw18.service;

import com.example.hw18.model.Car;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
//@Component
@Getter
@Setter
public class CarService {

    private int carNubmer;
    private final ModelGenerator modelGenerator;

    public CarService(ModelGenerator modelGenerator) {
        this.modelGenerator = modelGenerator;
    }

    public Car addNewCar(int carId) {
        ++carId;
        return Car
                .builder()
                .carId(carId)
                .userIdOvner(0)
                .model(modelGenerator.genereteName())
                .build();
    }


}