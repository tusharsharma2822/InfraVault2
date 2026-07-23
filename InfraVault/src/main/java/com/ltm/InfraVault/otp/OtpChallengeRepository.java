package com.ltm.InfraVault.otp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpChallengeRepository extends MongoRepository<OtpChallenge, String>{
    Optional<OtpChallenge>
    findTopByUserIdAndUsedFalseOrderByCreatedAtDesc(String userId);
}