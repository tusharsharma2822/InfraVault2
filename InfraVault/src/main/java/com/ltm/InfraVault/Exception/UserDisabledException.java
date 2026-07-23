package com.ltm.InfraVault.Exception;

public class UserDisabledException extends RuntimeException{
    public UserDisabledException(String message){
        super(message);
    }
}