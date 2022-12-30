package com.example.hw20.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelGenerator {

    private List<String> models = List.of("BMW", "Mersedes", "Opel", "Audi", "Volkswagen");

    public String genereteName() {
        return models.get(getRandomNumber(0, 5));

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random()) * (max - min) + min);
    }
}
