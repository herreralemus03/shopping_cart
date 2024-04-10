package com.hlstudios.products.services.clients;

import com.hlstudios.products.models.JwtRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Lazy
@FeignClient(name = "auth-service")
public interface AuthClientService {

    @PostMapping("/api/v1/security/auth")
    ResponseEntity<?> authenticate(
        @RequestBody JwtRequest jwtRequest
    );

    @GetMapping(value = "/api/v1/security/check")
    ResponseEntity<?> check(@RequestParam(name = "jwt") String token);

}
