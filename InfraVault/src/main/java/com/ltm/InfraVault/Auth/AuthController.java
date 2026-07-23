package com.ltm.InfraVault.Auth;

import com.ltm.InfraVault.Auth.dto.LoginRequest;
import com.ltm.InfraVault.Auth.dto.LoginResponse;
import com.ltm.InfraVault.Auth.dto.RegisterRequest;
import com.ltm.InfraVault.Auth.dto.RegisterResponse;
import com.ltm.InfraVault.User.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ){
        return authService.login(request);
    }
}