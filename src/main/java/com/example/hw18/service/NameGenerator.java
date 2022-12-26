package com.example.hw18.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NameGenerator {
    private List<String> names = List.of("Alex", "Bill", "Garry", "Yohan", "Klaus");

    public String genereteName() {
        return names.get(getRandomNumber(0, 5));

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random()) * (max - min) + min);
    }
}

