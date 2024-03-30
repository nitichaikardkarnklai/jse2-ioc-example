package com.usermanagement.usermanagement.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequestDTO request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
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

// User Request DTO (Data Transfer Object)
record UserRequest(
        String name,
        int age) {
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

class UserRequestDTO {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    String name;

    @Min(value = 1, message = "Invalid Age: Equals to zero or Less than zero")
    @Max(value = 100, message = "Invalid Age: Exceeds 100 years")
    Integer age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return  age;
    }
}