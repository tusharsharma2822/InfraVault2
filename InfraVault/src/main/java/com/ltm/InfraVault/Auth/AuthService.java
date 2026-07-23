package com.ltm.InfraVault.Auth;

import com.ltm.InfraVault.Auth.dto.LoginRequest;
import com.ltm.InfraVault.Auth.dto.LoginResponse;
import com.ltm.InfraVault.Auth.dto.RegisterRequest;
import com.ltm.InfraVault.Exception.EmailAlreadyExistsException;
import com.ltm.InfraVault.Exception.InvalidCredentialsException;
import com.ltm.InfraVault.Exception.UserDisabledException;
import com.ltm.InfraVault.User.User;
import com.ltm.InfraVault.User.UserRepository;
import com.ltm.InfraVault.Auth.dto.RegisterResponse;
import com.ltm.InfraVault.otp.OtpService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            OtpService otpService
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
    }

    public RegisterResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(
                    "User with this email is already exists"
            );
        }

        String passwordHash = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getFullName(),
                request.getEmail(),
                passwordHash,
                "USER",
                "ACTIVE",
                false
        );

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                "User has been successfully registered"
        );
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                    new InvalidCredentialsException(
                        "Invalid email or password"
                    )
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash()
        )){
            throw new InvalidCredentialsException(
                "Invalid email or password"
            );
        }

        if (!"ACTIVE".equals(user.getStatus())){
            throw new UserDisabledException(
                "User account is not active"
            );
        }

        String otp = otpService.createOTPChallenge(user);

        System.out.println("Generated OTP: " + otp);

        return new LoginResponse(
                "Credentials verified. OTP generated",
                user.getEmail(),
                true
        );
    }
}