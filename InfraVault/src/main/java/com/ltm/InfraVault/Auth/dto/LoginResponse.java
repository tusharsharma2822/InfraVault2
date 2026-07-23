package com.ltm.InfraVault.Auth.dto;

public class LoginResponse {
    private String message;
    private String mail;
    private boolean otpRequired;

    public LoginResponse(
            String message,
            String mail,
            boolean otpRequired
    ){
        this.message = message;
        this.mail = mail;
        this.otpRequired = otpRequired;
    }

    public String getMessage() {
        return message;
    }

    public String getMail() {
        return mail;
    }

    public boolean isOtpRequired() {
        return otpRequired;
    }
}