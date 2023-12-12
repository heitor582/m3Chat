package com.startup.m3.services;

import com.startup.m3.dto.SignupDto;
import com.startup.m3.entity.User;
import com.startup.m3.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserInfoService(@Lazy UserRepository repository, @Lazy PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userDetail = repository.findByEmail(email);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public User addUser(SignupDto signupDto) {
        return repository.save(User.newUser(signupDto.email(), encoder.encode(signupDto.password()), signupDto.companyName()));
    }

    public User findBy(final String email) {
        return repository.findByEmail(email).orElseThrow();
    }


}