package com.example.hw18.controller;

import com.example.hw18.model.Car;
import com.example.hw18.model.User;
import com.example.hw18.service.CarService;
import org.springframework.stereotype.Controller;
import com.example.hw18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/garageWeb")
public class ControllerWeb {
    private final CarService carService;
    private final UserService userService;

    @Autowired
    public ControllerWeb(CarService carService, UserService userService) {
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
            add(Car.builder().carId(1).userIdOvner(0).model("Ferari").build());
            add(Car.builder().carId(2).userIdOvner(0).model("Ferari").build());
            add(Car.builder().carId(3).userIdOvner(0).model("Ferari").build());
        }
    };

    @GetMapping("/allCars")
    public String getAll(Model model) {
        model.addAttribute("allCars", cars);
        return "allCars";
    }

    @GetMapping("/allUsers")
    public String getAllgarage(Model model) {
        model.addAttribute("allUsers", garage);
        return "allUsers";
    }


    @PutMapping("/newUser")
    public @ResponseBody User createUser(@RequestBody User user) {

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
    public @ResponseBody Car createCar(@RequestBody Car car) {

        for (Car car1 : cars) {
            if (car.getCarId() == car1.getCarId()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "car with this id is arleady exist");
            }
        }
        cars.add(car);
        return car;
    }

    @PutMapping("/newRandomCar")
    public @ResponseBody Car randomCar() {
        cars.add(carService.addNewCar(cars.size()));
        return carService.addNewCar(cars.size());
    }

    @PutMapping("/newRandomUser")
    public @ResponseBody User randomUser() {
        garage.put(userService.addUser(garage.size()), new ArrayList<Car>());
        return userService.addUser(garage.size());

    }

    @PostMapping("/makeOvners/{userId}/{carId}")
    public @ResponseBody User makeOvners(@PathVariable(value = "userId") int userId,
                                         @PathVariable(value = "carId") int carId) {

        garage.get(userService.findUserById(garage, userId)).add(cars.get(carId));

        for (Car car : garage.get(userService.findUserById(garage, userId))) {
            if (car.getCarId() == carId) {
                car.setUserIdOvner(userId);
            }
        }
        return userService.findUserById(garage, userId);
    }

    @DeleteMapping("/deleteCar/{carId}")
    public @ResponseBody List<Car> deleteCar(@PathVariable(value = "carId") int carId) {
        cars.removeIf(car -> car.getCarId() == carId);
        return cars;
    }

    @DeleteMapping("/deleteUser/{userId}")
    public @ResponseBody Map<User, ArrayList<Car>> deleteUser(@PathVariable(value = "userId") int userId) {
        for (User user : garage.keySet())
            if (user.getUserId() == userId)
                garage.remove(user);
        return garage;

    }

    @PostMapping("/updateUser/{userId}")
    public @ResponseBody User updateUser(@RequestBody User user,
                                         @PathVariable(value = "userId") int userId) {
        for (Map.Entry<User, ArrayList<Car>> item : garage.entrySet()) {
            if (item.getKey().equals(userService.findUserById(garage, userId))) {
                item.getKey().setName(user.getName());
            }
            return item.getKey();
        }
        return null;
    }

    @PostMapping("/updateCar/{carId}")
    public @ResponseBody Car updateCar(@RequestBody Car car,
                                       @PathVariable(value = "carId") int carId) {
        for (Car car1 : cars) {
            if (car1.getCarId() == carId) {

                car1.setModel(car.getModel());
                car1.setUserIdOvner(car.getUserIdOvner());

                for (Map.Entry<User, ArrayList<Car>> item : garage.entrySet()) {
                    if (item.getValue().contains(car1)) {
                        item.getValue().remove(car1);
                    }
                }

                for (Map.Entry<User, ArrayList<Car>> item : garage.entrySet()) {
                    if (item.getKey().equals(userService.findUserById(garage, car1.getUserIdOvner()))) {
                        item.getValue().add(car1);
                    }
                }
            }
        }
        return car;
    }
}