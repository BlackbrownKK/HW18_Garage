package com.example.hw18.service;

import com.example.hw18.model.Car;
import com.example.hw18.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Getter
@Setter
public class UserService {

    private int userNumber;
    private final NameGenerator nameGenerator;

    public UserService(NameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

    public User addUser(int userId) {
        ++userId;
        return User
                .builder()
                .name(nameGenerator.genereteName())
                .userId(userId)
                .build();
    }

    public User findUserById(Map<User, ArrayList<Car>> garage, int id) {
        for (Map.Entry<User, ArrayList<Car>> item : garage.entrySet()) {
            if (item.getKey().getUserId() == id)
                return item.getKey();
        }
        return null;
    }
}






