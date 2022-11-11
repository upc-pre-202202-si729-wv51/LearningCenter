package com.acme.learningcenter.security.service;

import com.acme.learningcenter.security.domain.model.entity.User;
import com.acme.learningcenter.security.domain.persistence.RoleRepository;
import com.acme.learningcenter.security.domain.persistence.UserRepository;
import com.acme.learningcenter.security.domain.service.UserService;
import com.acme.learningcenter.security.domain.service.communication.AuthenticateRequest;
import com.acme.learningcenter.security.domain.service.communication.AuthenticateResponse;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }



    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

}
