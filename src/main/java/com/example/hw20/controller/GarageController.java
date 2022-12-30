package com.example.hw20.controller;

import com.example.hw20.service.GarageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/garage")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("garage", garageService.getAll());
        return "garage";
    }
}
