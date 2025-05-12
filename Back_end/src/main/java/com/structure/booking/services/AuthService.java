package com.structure.booking.services;

import com.structure.booking.domain.payload.request.LoginRequest;
import com.structure.booking.domain.payload.request.SignUpRequest;
import com.structure.booking.domain.payload.response.AuthResponse;
import com.structure.booking.domain.payload.response.UserResponse;

public interface AuthService {
    void registerAccount(SignUpRequest signUpRequest);

    AuthResponse login(LoginRequest loginRequest);

    UserResponse getInformationOfAccount();
}
