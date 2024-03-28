package com.usermanagement.usermanagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // READ›
    // GET: http://localhost:8080/api/users
    @GetMapping("")
    public List<User> getUsers(@RequestParam("active") Optional<Boolean> active) {
        return userService.getUsers(active);
    }

    // CREATE
    // POST: http://localhost:8080/api/users
    // req body => {name, age}
    @PostMapping("")
    public User createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    // UPDATE
    // PUT: http://localhost:8080/api/users/{id}
    // req body => {name}
    @PutMapping("/{id}")
    public Message editUser(@PathVariable("id") int id, @RequestBody UserRequest request) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            User u = user.get();
            userService.updateUser(u, request);
            return new Message("user updated");
        } else {
            return new Message("id not found");
        }
    }

    // DELETE
    // DELETE: http://localhost:8080/api/users/{id}
    @DeleteMapping("/{id}")
    public Message removeUser(@PathVariable("id") int id) {
        Boolean isRemoved = userService.deleteUser(id);
        return isRemoved ? new Message("user deleted") : new Message("id not found");
    }
}

record UserRequest(String name, int age) {
}

record Message(String message) {
}

class User {
    private int id;
    private String name;
    private int age;
    private Boolean active;

    public User(int id, String name, int age, Boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public Boolean getActive() {
        return active;
    }
}