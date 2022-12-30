package com.example.hw20.repasitory;


import com.example.hw20.model.Car;
import com.example.hw20.model.User;
import com.example.hw20.repasitory.Dao.CarRepasitory;
import com.example.hw20.repasitory.mapper.CarMapper;
import com.example.hw20.repasitory.mapper.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepasitoryImp implements CarRepasitory {
    private final JdbcTemplate jdbcTemplate;

    private static final String save =
            """
                    INSERT INTO public.car(
                    user_id_ovner, model)
                    VALUES (?, ?)
                    """;

    public CarRepasitoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Car findById(int id) {
        try {
            return jdbcTemplate.queryForObject("Select * from car where car_id = " + id, new CarMapper());
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("car with this id " + id + " not found!");
        }
        return null;
    }


    public List<Car> getAll() {
        return jdbcTemplate.query("Select * from car", new CarMapper());
    }

    public Car save(Car car) {
        jdbcTemplate.update(save, car.getUserIdOvner(), car.getModel());
        return car;
    }

    public void deleteCar(int id) {
        jdbcTemplate.update("DELETE FROM car WHERE car_id = ?", id);
    }

    public void updateCar(Car car, int id) {
        for (Car car1 : jdbcTemplate.query("Select * from car", new CarMapper())) {
            if (car1.getUserIdOvner() == car.getUserIdOvner()) {
                jdbcTemplate.update("UPDATE car SET model = ? WHERE car_id = ? ", car.getModel(), id);
                jdbcTemplate.update("UPDATE car SET user_id_ovner = ? WHERE car_id = ? ", car.getUserIdOvner(), id);
            } else {
                System.out.println("user with this id " + car.getUserIdOvner() + " not found!");
            }
        }
    }
}
