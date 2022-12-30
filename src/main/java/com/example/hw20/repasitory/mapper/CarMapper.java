package com.example.hw20.repasitory.mapper;


import com.example.hw20.model.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car>{

   @Override
    public Car mapRow (ResultSet rows, int rowNum) throws SQLException{

        return Car.builder()
                .carId(rows.getInt("car_id"))
                .userIdOvner(rows.getInt("user_id_ovner"))
                .model(rows.getString("model"))
                .build();
    }

}
