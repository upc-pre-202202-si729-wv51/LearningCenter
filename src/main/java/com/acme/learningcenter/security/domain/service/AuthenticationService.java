package com.acme.learningcenter.security.domain.service;

import com.acme.learningcenter.security.domain.service.communication.AuthenticateRequest;
import com.acme.learningcenter.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterRequest request);
}
