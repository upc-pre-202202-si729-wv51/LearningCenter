package com.acme.learningcenter.security.service;

import com.acme.learningcenter.security.domain.service.communication.AuthenticateRequest;
import com.acme.learningcenter.security.domain.service.communication.AuthenticateResponse;
import com.acme.learningcenter.security.domain.service.AuthenticationService;
import com.acme.learningcenter.security.domain.service.communication.RegisterRequest;
import com.acme.learningcenter.security.middleware.JwtHandler;
import com.acme.learningcenter.security.middleware.UserDetailsImpl;
import com.acme.learningcenter.security.resource.AuthenticateResource;
import com.acme.learningcenter.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final JwtHandler handler;

    private final PasswordEncoder encoder;

    private final EnhancedModelMapper mapper;




    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtHandler handler, PasswordEncoder encoder, EnhancedModelMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.handler = handler;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()));
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            String token = handler.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            AuthenticateResource resource = mapper.map(userDetails, AuthenticateResource.class);
            resource.setRoles(roles);
            resource.setToken(token);
            AuthenticateResponse response = new AuthenticateResponse(resource);
            return ResponseEntity.ok(response.getResource());
        } catch (Exception e) {
            AuthenticateResponse response = new AuthenticateResponse(
                    String.format("An error occurred while authenticating: %s", e.getMessage()));
            return ResponseEntity.badRequest().body(response.getMessage());
        }

    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        return null;
    }
}
