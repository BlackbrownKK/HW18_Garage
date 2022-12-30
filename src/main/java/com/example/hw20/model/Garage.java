package com.example.hw20.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Garage {

    private int carId;
    private int userIdOvner;
    private String model;
    private int userId;
    private String name;
}
