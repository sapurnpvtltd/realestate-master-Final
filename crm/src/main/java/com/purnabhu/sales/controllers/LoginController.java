package com.purnabhu.sales.controllers;

import com.purnabhu.sales.jwt.JwtUtils;
import com.purnabhu.sales.request.JwtRequest;
import com.purnabhu.sales.response.JwtResponse;
import com.purnabhu.sales.response.ResponseEntityObject;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/loginauth")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;
    @Value("${spring.security.username}")
    private String username;

    @Value("${spring.security.password}")
    private String password;
    @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest jwtRequest) {
        this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserDetails userDetails = null;
        if(jwtRequest.getUsername().equals(username))
             userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signout")
    public ResponseEntityObject logoutUser() {
        //ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        ResponseEntityObject responseEntityObject = new ResponseEntityObject();
        responseEntityObject.setResponseCode(200);
        responseEntityObject.setResponseMessage("You've been signed out!");
        return responseEntityObject;
    }

    private void doAuthenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
