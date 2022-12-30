package com.example.hw20.repasitory.Dao;

import com.example.hw20.model.User;

import java.util.List;

public interface UserRepasitory {
    User findById(int id);

    List<User> getAll();

    User save(User user);

    void updateUser(User user, int id);

    void deleteUser(int id);
}
