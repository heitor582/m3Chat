package com.startup.m3.controller;

import com.startup.m3.dto.AuthRequest;
import com.startup.m3.dto.CreateUserDto;
import com.startup.m3.dto.SignupDto;
import com.startup.m3.entity.User;
import com.startup.m3.services.JwtService;
import com.startup.m3.services.UserInfoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserInfoService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserInfoService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public CreateUserDto addNewUser(@RequestBody SignupDto signupDto) {
        return CreateUserDto.from(userService.addUser(signupDto));
    }

    @PostMapping("/signin")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.email());
        } else {
            throw new UsernameNotFoundException("invalid user request!");
        }
    }
}
