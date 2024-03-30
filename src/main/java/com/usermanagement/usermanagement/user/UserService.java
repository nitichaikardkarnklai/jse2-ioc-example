package com.usermanagement.usermanagement.user;

import com.usermanagement.usermanagement.mail.MailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final MailService mailService;
    List<User> users = new ArrayList<>(
            List.of(
                    new User(1, "John Doe", 28, true),
                    new User(2, "Jane Doe", 29, false),
                    new User(3, "Joe Doe", 32, true)
            )
    );

    public UserService(@Qualifier("googleMail") MailService mailService) {
        this.mailService = mailService;
    }

    public List<User> getUsers(Optional<Boolean> active) {
        if (active.isPresent()) {
            return users.stream().filter(u -> u.getActive() == active.get()).collect(Collectors.toList());
        }
        return users;
    }

    public User createUser(UserRequestDTO request) {
        Optional<Integer> maxId = users.stream().map(User::getId).max(Integer::compareTo);
        int newId = maxId.orElse(0) + 1;
        User newUser = new User(newId, request.getName(), request.getAge(), true);
        users.add(newUser);

        //send email
        mailService.sentEmail("dev@gmail.com", "User created");

        return newUser;
    }

    public Optional<User> findUserById(int id) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        return user;
    }

    public void updateUser(User u, UserRequest request) {
        u.setName(request.name());
    }

    public Boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}
