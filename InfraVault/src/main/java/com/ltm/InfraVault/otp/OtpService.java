package com.ltm.InfraVault.otp;

import com.ltm.InfraVault.User.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpService {
    private final OtpChallengeRepository otpChallengeRepository;
    private final PasswordEncoder passwordEncoder;

    private final SecureRandom secureRandom = new SecureRandom();

    public OtpService(
            OtpChallengeRepository otpChallengeRepository,
            PasswordEncoder passwordEncoder
    ){
        this.otpChallengeRepository = otpChallengeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createOTPChallenge(User user){
        int otpNumber = 100000 + secureRandom.nextInt(900000);
        String otp = String.valueOf(otpNumber);
        String otpHash = passwordEncoder.encode(otp);

        OtpChallenge otpChallenge = new OtpChallenge(
                user.getId(),
                otpHash,
                LocalDateTime.now().plusMinutes(5),
                false
        );

        otpChallengeRepository.save(otpChallenge);

        return otp;
    }
}
