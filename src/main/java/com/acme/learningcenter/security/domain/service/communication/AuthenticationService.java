package com.acme.learningcenter.security.domain.service.communication;

import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterRequest request);
}
