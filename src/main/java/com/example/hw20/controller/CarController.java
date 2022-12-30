package com.example.hw20.controller;


import com.example.hw20.model.Car;
import com.example.hw20.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public String getCar(Model model, @PathVariable int id)   {
        model.addAttribute("car", carService.getById(id));
        return "Car";

    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("allCars", carService.getAll());
        return "allCars";
    }

    @PutMapping("/new")
    public String saveNewCar(Model model, @RequestBody Car car) {
        model.addAttribute("car", carService.addCar(car));
        return "Car";
    }

    @PostMapping("/{id}")
    public String updateCar(Model model, @PathVariable int id, @RequestBody Car car)   {
        carService.updateCar(car, id);
        model.addAttribute("car", carService.getById(id));
        return "Car";

    }

    @PutMapping("/random")
    public String saveNewRandomCar(Model model) {
 carService.addCar(carService.addRandomCar());
        model.addAttribute("car", carService.addRandomCar());
        return "Car";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(Model model, @PathVariable int id)   {
        carService.deleteCar(id);
        model.addAttribute("allCars", carService.getAll());
        return "allCars";
    }
}
