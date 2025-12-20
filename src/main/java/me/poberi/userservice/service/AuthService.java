package me.poberi.userservice.service;

import lombok.RequiredArgsConstructor;
import me.poberi.userservice.dto.LoginRequest;
import me.poberi.userservice.dto.RegisterRequest;
import me.poberi.userservice.dto.UserResponse;
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
    private final JwtService jwtService;

    public String login(LoginRequest req) {
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

        return jwtService.generateToken(user.getId(), user.getUsername());

    }

    public UserResponse register(RegisterRequest req) {
        // Check if username exists
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        // Check if email exists
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Create new user
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setDriver(req.isDriver());

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.isDriver()
        );
    }

}
