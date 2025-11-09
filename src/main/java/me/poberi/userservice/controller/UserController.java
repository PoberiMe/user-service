package me.poberi.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.UserRegistration;
import me.poberi.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody UserRegistration user) {
        userService.createUser(user);
    }
}
