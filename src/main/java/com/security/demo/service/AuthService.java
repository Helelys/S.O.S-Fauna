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
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getSenha())) {
            return jwtUtil.generateToken(username);
        }
        return "Invalid credentials";
    }

    public String authenticateOng(String email, String password) {
        Optional<Ong> ong = ongRepository.findByEmail(email);
        if (ong.isPresent() && passwordEncoder.matches(password, ong.get().getSenha())) {
            return jwtUtil.generateToken(email);
        }
        return "Invalid credentials";
    }

    public void registerUser(String email, String senha) {
        User user = new User(null, email, passwordEncoder.encode(senha));
        userRepository.save(user);
    }

    public void registerOng(String email, String senha) {
        Ong ong = new Ong(null, email, passwordEncoder.encode(senha));
        ongRepository.save(ong);
    }
}
