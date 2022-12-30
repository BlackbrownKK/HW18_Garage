package com.example.hw20.service;

import com.example.hw20.model.Garage;
import com.example.hw20.repasitory.Dao.GarageRepasitory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Getter
@Setter
public class GarageService {
    private final GarageRepasitory garageRepasitory;

    public GarageService(GarageRepasitory garageRepasitory) {
        this.garageRepasitory = garageRepasitory;
    }

    public List<Garage> getAll() {
        return garageRepasitory.getAll();
    }
}
