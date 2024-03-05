package com.example.demo.providers;

import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    UserRepo userRepo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authenticate called");
        Optional<com.example.demo.model.User> oUser = Optional.ofNullable(userRepo.findByEmail(authentication.getName()).orElseThrow(()-> new UsernameNotFoundException("not found")));
        System.out.println(authentication.getName());
        System.out.println(authentication.getCredentials());
        UserDetails principal = org.springframework.security.core.userdetails.User.builder().username(oUser.get().email).password(oUser.get().pass).build();
        return new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
