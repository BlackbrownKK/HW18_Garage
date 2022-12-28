package com.example.hw18.controller;

import com.example.hw18.model.Car;
import com.example.hw18.model.User;
import com.example.hw18.service.CarService;
import com.example.hw18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/garage")
public class Controller {
    private final CarService carService;
    private final UserService userService;

    @Autowired
    public Controller(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    private final Map<User, ArrayList<Car>> garage = new HashMap<>() {
        {
            put((User.builder().userId(0).name("Fill").build()), new ArrayList<Car>());
            put((User.builder().userId(1).name("Den").build()), new ArrayList<Car>());
            put((User.builder().userId(2).name("Bob").build()), new ArrayList<Car>());
            put((User.builder().userId(3).name("Bill").build()), new ArrayList<Car>());
        }
    };

    private final List<Car> cars = new ArrayList<Car>() {
        {
            add(Car.builder().carId(0).userIdOvner(0).model("Ferari").build());
            add(Car.builder().carId(1).userIdOvner(1).model("Ferari").build());
            add(Car.builder().carId(2).userIdOvner(2).model("Ferari").build());
            add(Car.builder().carId(3).userIdOvner(3).model("Ferari").build());
        }
    };




    @GetMapping("/allCars")
    public List<Car> getAll() {
        return cars;
    }


    @GetMapping("/allUsers")
        public Map<User, ArrayList<Car>> getAllgarage() {
            return garage;
        }

    @PutMapping("/newUser")
    public User createUser(@RequestBody User user) {

        for (Map.Entry<User, ArrayList<Car>> item : garage.entrySet()) {
            if (item.getKey().equals(user)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "user with this name is arleady exist");
            }
        }
        garage.put(user, new ArrayList<Car>());
        return user;
    }

    @PutMapping("/newCar")
    public Car createCar(@RequestBody Car car) {

        for (Car car1 : cars) {
            if (car.getCarId() == car1.getCarId()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "car with this id is arleady exist");
            }
        }
        cars.add(car);

        return car;
    }

    @PostMapping("/makeOvners/{userId}/{carId}")
    public User makeOvners(@PathVariable(value = "userId") int userId,
                           @PathVariable(value = "carId") int carId) {

        garage.get(userService.findUserById(garage, userId)).add(cars.get(carId));

        for (Car car : garage.get(userService.findUserById(garage, userId))) {
            if (car.getCarId() == carId) {
                car.setUserIdOvner(userId);
            }
        }
        return userService.findUserById(garage, userId);
    }

    @PostMapping("/deleteCar/{carId}")
    public List<Car> deleteCar(@PathVariable(value = "carId") int carId) {
        cars.removeIf(car -> car.getCarId() == carId);
        return cars;
    }

    @PostMapping("/deleteUser/{userId}")
    public User deleteUser(@PathVariable(value = "userId") int userId) {
        for (User user : garage.keySet()) {
            if (user.getUserId() == userId)
                garage.remove(user);
            return user;
        }
        return null;
    }

    @PostMapping("/updateUser/{userId}")
    public User updateUser(@RequestBody User incomingData,
                           @PathVariable(value = "userId") int userId) {
        for (Map.Entry<User, ArrayList<Car>> item : garage.entrySet()) {
            if (item.getKey().equals(userService.findUserById(garage, userId))) {
                item.getKey().setName(incomingData.getName());
            }
            return item.getKey();
        }
        return null;
    }

    @PostMapping("/updateCar/{carId}")
    public Car updateCar(@RequestBody Car incomingData,
                         @PathVariable(value = "carId") int carId) {
        for (Car car : cars) {
            if (car.getCarId() == carId) {
                car.setModel(incomingData.getModel());
                car.setUserIdOvner(incomingData.getUserIdOvner());
                return car;
            }
        }
        return null;
    }
}

