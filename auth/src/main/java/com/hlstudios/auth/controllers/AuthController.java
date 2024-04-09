package com.hlstudios.auth.controllers;

import com.hlstudios.auth.models.JwtRequest;
import com.hlstudios.auth.models.JwtResponse;
import com.hlstudios.auth.services.auth.JwtUserDetailsService;
import com.hlstudios.auth.utils.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/security")
public class AuthController {

    final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtTokenUtil;
    final JwtUserDetailsService userDetailsService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            JwtUserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<?> createAuthenticationToken(
        @RequestBody JwtRequest jwtRequest
    ) throws Exception {
        try {
            String username = jwtRequest.getUsername();
            String password = jwtRequest.getPassword();

            authenticate(username, password);

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(username);

            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(path = "/check")
    public ResponseEntity<?> check(
        @RequestParam(name = "jwt") String token
    ){
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(username);

            Boolean valid = jwtTokenUtil.validateToken(token.substring(7), userDetails);

            if(!valid) throw new Exception("Invalid token");

            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("message", "ok");
            }});
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).body(new HashMap<String, Object>(){{
                put("message", "unauthorized");
            }});
        }
    }

    private void authenticate(
            String username,
            String password
    ) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
