package com.exclaimation.librarysystem.dto;

import lombok.Getter;
import lombok.Setter;

public class Auth {
    @Getter
    @Setter
    public static class RegisterRequest {
        private String userId;
        private String userName;
        private String password;
        private String phoneNumber;

    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String id;
        private String pw;

    }

}
