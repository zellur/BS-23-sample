package com.zellur.brainstation23.controller;

import com.zellur.brainstation23.entity.User;
import com.zellur.brainstation23.model.JwtRequest;
import com.zellur.brainstation23.model.JwtResponse;
import com.zellur.brainstation23.service.JwtUserDetailsService;
import com.zellur.brainstation23.service.UserService;
import com.zellur.brainstation23.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@Slf4j
@RestController
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final UserService userService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager,
                                       JwtTokenUtil jwtTokenUtil,
                                       JwtUserDetailsService userDetailsService, UserService userService1) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService1;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Callable<ResponseEntity<?>> createAuthenticationToken(@RequestBody JwtRequest jwtAuthRequest) throws Exception {
        return () -> {
            log.debug("Received login request: {}", jwtAuthRequest);
            authenticate(jwtAuthRequest.getUsername().trim(), jwtAuthRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername().trim());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token, token, 0L, userDetails.getAuthorities().toString(), ""));
        };
    }
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public Callable<ResponseEntity<User>> registerUser(@RequestBody User user) {
        log.debug("Received request for registration: {}", user);
        return () -> ResponseEntity.ok(userService.save(user));
    }
    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public Callable<ResponseEntity<Boolean>> logout(@RequestBody JwtResponse token) {
        log.debug("Received logout request for token: {}", token);
        return () -> ResponseEntity.ok(jwtTokenUtil.removeToken(token.getAccess_token()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
