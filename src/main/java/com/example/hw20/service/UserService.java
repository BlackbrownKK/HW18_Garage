package com.example.hw20.service;

import com.example.hw20.model.User;
import com.example.hw20.repasitory.Dao.UserRepasitory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class UserService {
    private final UserRepasitory userRepasitory;

    public UserService(UserRepasitory userRepasitory) {
        this.userRepasitory = userRepasitory;
    }

    public User getById(int id) {
        return this.userRepasitory.findById(id);
    }

    public List<User> getAll() {
        return userRepasitory.getAll();
    }

    public User addUser(User user) {
        return this.userRepasitory.save(user);
    }

    public void deleteUser(int id) {
        userRepasitory.deleteUser(id);
    }

    public void updateUser(User user, int id) {
        userRepasitory.updateUser(user, id);
    }
}
