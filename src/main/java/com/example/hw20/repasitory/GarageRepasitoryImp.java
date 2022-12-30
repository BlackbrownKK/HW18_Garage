package com.example.hw20.repasitory;

import com.example.hw20.model.Garage;
import com.example.hw20.repasitory.Dao.GarageRepasitory;
import com.example.hw20.repasitory.mapper.GarageMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class GarageRepasitoryImp implements GarageRepasitory {

    private final JdbcTemplate jdbcTemplate;

    public GarageRepasitoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String innerJoinGarage =
            """
            Select * from public.user
            INNER JOIN car on
            car.user_id_ovner = public.user.user_id
            """;

    public List<Garage> getAll() {
//        return jdbcTemplate.query("Select * from public.user INNER JOIN car on car.user_id_ovner = public.user.user_id", new GarageMapper());
        return jdbcTemplate.query(innerJoinGarage, new GarageMapper());
    }
}
