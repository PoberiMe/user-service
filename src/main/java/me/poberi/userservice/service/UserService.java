package me.poberi.userservice.service;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.UserRequest;
import me.poberi.userservice.dto.UserResponse;
import me.poberi.userservice.model.User;
import me.poberi.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setDriver(req.isDriver());

        userRepository.save(user);
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
