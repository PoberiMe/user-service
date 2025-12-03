package me.poberi.userservice.service;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.LoginRequest;
import me.poberi.userservice.model.User;
import me.poberi.userservice.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void login(LoginRequest req) {
        User user;
        if (req.getEmail() != null) {
            user = userRepository.findUserByEmail(req.getEmail());
        } else if (req.getUsername() != null) {
            user = userRepository.findUserByUsername(req.getUsername());
        } else {
            throw new IllegalArgumentException("Both username and email must not be null");
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        // TODO: create and return token

    }
}
