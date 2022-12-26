package com.example.hw18.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {
    private int carId;
    private int userIdOvner;
    private String model;
}
