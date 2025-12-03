package me.poberi.userservice.controller;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.LoginRequest;
import me.poberi.userservice.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public void login(@RequestBody LoginRequest req) {
        authService.login(req);
    }
}
