package me.poberi.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.UserResponse;
import me.poberi.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

}
