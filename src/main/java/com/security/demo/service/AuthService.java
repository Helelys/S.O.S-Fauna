package com.security.demo.service;

import com.security.demo.model.entity.User;
import com.security.demo.model.entity.Ong;
import com.security.demo.repository.UserRepository;
import com.security.demo.repository.OngRepository;
import com.security.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final OngRepository ongRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, OngRepository ongRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.ongRepository = ongRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return jwtUtil.generateToken(username);
        }
        return "Invalid credentials";
    }

    public String authenticateOng(String name, String password) {
        Optional<Ong> ong = ongRepository.findByName(name);
        if (ong.isPresent() && passwordEncoder.matches(password, ong.get().getPassword())) {
            return jwtUtil.generateToken(name);
        }
        return "Invalid credentials";
    }

    public void registerUser(String username, String password) {
        User user = new User(null, username, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void registerOng(String name, String password) {
        Ong ong = new Ong(null, name, passwordEncoder.encode(password));
        ongRepository.save(ong);
    }
}
