package com.example.hw20.controller;

import com.example.hw20.model.User;
import com.example.hw20.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("Users", userService.getAll());
        return "Users";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.getById(id));

        return "User";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(Model model, @PathVariable int id)   {
        userService.deleteUser(id);
        model.addAttribute("Users", userService.getAll());
        return "Users";
    }

    @PutMapping("/new")
    public String saveNewUser(Model model, @RequestBody User user) {
        model.addAttribute("user", userService.addUser(user));
        return "User";
    }

    @PostMapping("/{id}")
    public String updateUser(Model model, @PathVariable int id, @RequestBody User user)   {
        userService.updateUser(user, id);
        model.addAttribute("user", userService.getById(id));
        return "User";
    }
}
