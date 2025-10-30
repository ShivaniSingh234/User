package com.example.UserService.controller;
import com.example.UserService.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

	private Map<Long, User> users = new HashMap<>();

    public UserController() {
        // pre-populate
        users.put(1L, new User(1L, "Shivani", "shivani@example.com"));
        users.put(2L, new User(2L, "Riya", "riya@example.com"));
    }

    @GetMapping
    public Collection<User> getAll() {
        return users.values();
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return users.containsKey(id)
                ? "User found: " + users.get(id).getName()
                : "User not found!";
    }

    @PostMapping
    public String addUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "User added successfully!";
    }
}
