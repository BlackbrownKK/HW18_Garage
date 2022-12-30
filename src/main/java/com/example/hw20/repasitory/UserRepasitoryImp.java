package com.example.hw20.repasitory;

import com.example.hw20.model.User;
import com.example.hw20.repasitory.Dao.UserRepasitory;
import com.example.hw20.repasitory.mapper.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepasitoryImp implements UserRepasitory {
    private final JdbcTemplate jdbcTemplate;


    public UserRepasitoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String save =
            """
                    INSERT INTO public.user(
                    name)
                    VALUES (?)
                    """;

    public User findById(int id) {
        try {
            return jdbcTemplate.queryForObject("Select * from public.user where user_id = " + id, new UserMapper());
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("user with this id " + id + " not found!");
        }
        return null;
    }


    public List<User> getAll() {
        return jdbcTemplate.query("Select * from public.user", new UserMapper());
    }

    public User save(User user) {
        jdbcTemplate.update(save, user.getName());
        return user;
    }

    public void deleteUser(int id) {
            jdbcTemplate.update("DELETE FROM public.user WHERE user_id = ?", id);
    }

    public void updateUser(User user, int id) {
        jdbcTemplate.update("UPDATE public.user SET name = ? WHERE user_id = ? ", user.getName(), id);
    }
}
