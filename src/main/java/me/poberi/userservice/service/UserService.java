package me.poberi.userservice.service;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.UserRegistration;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public void createUser(UserRegistration user) {
        System.out.println("Create user" + user);
    }
}
