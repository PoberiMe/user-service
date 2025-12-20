package me.poberi.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.LoginRequest;
import me.poberi.userservice.dto.RegisterRequest;
import me.poberi.userservice.dto.UserResponse;
import me.poberi.userservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public String login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("register")
    public UserResponse register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

}
