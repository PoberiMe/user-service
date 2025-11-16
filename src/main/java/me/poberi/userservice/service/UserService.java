package me.poberi.userservice.service;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.UserRequest;
import me.poberi.userservice.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    public void createUser(UserRequest user) {
        System.out.println("Create user" + user);
    }

    public UserResponse getUser(int id) {
        return UserResponse.builder()
                .id(id)
                .username("User")
                .email("mock@example.com")
                .isDriver(false)
                .build();
    }
}
