package org.example.authservice.service;

import lombok.RequiredArgsConstructor;
import org.example.authservice.feign.UserFeign;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserFeign repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = repository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }

}