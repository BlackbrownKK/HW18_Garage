package com.example.hw20.repasitory.mapper;

import com.example.hw20.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rows, int rowNum) throws SQLException {

        return User.builder()
                .userId(rows.getInt("user_id"))
                .name(rows.getString("name"))
                .build();
    }
}
