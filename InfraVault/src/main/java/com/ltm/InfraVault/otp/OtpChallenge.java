package com.ltm.InfraVault.otp;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "otp_challenges")
public class OtpChallenge {
    @Id
    private String id;

    private String userId;

    private String otpHash;

    private LocalDateTime expiresAt;

    private boolean used;

    @CreatedDate
    private LocalDateTime createdAt;

    public OtpChallenge() {
    }

    public OtpChallenge(
            String userId,
            String otpHash,
            LocalDateTime expiresAt,
            boolean used
    ){
        this.userId = userId;
        this.otpHash = otpHash;
        this.expiresAt = expiresAt;
        this.used = used;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getOtpHash() {
        return otpHash;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed(){
        return used;
    }

    public void setUsed(boolean used){
        this.used = used;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}