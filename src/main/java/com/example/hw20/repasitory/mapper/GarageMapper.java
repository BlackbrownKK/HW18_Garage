package com.example.hw20.repasitory.mapper;

import com.example.hw20.model.Garage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GarageMapper implements RowMapper<Garage> {
    @Override
    public Garage mapRow(ResultSet rows, int rowNum) throws SQLException {

        return Garage.builder()
                .userId(rows.getInt("user_id"))
                .name(rows.getString("name"))
                .carId(rows.getInt("car_id"))
                .userIdOvner(rows.getInt("user_id_ovner"))
                .model(rows.getString("model"))
                .build();
    }
}
