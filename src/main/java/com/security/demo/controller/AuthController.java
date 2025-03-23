package com.security.demo.controller;

import com.security.demo.model.dto.LoginOrgaoDTO;
import com.security.demo.model.dto.LoginUsuarioDTO;
import com.security.demo.model.dto.RegistrarOrgaoDTO;
import com.security.demo.model.dto.RegistrarUsuarioDTO;
import com.security.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/user")
    public String registerUser(@RequestBody RegistrarUsuarioDTO body) {
        authService.registerUser(body.getUsername(), body.getPassword());
        return "User registered successfully";
    }

    @PostMapping("/register/ong")
    public String registerOng(@RequestBody RegistrarOrgaoDTO body) {
        authService.registerOng(body.getUsername(), body.getPassword());
        return "ONG registered successfully";
    }

    @PostMapping("/login/user")
    public String loginUser(@RequestBody LoginUsuarioDTO body) {
        return authService.authenticateUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login/ong")
    public String loginOng(@RequestBody LoginOrgaoDTO body) {
        return authService.authenticateOng(body.getUsername(), body.getPassword());
    }
}
