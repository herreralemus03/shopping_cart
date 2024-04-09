package com.hlstudios.orders.components;

import com.hlstudios.orders.services.clients.AuthClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthClientService authClientService;

    public AuthInterceptor(AuthClientService authClientService) {
        this.authClientService = authClientService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        boolean isAuthenticated = false;
        log.info("Requested uri: ".concat(request.getRequestURI()));

        final String requestTokenHeader = request.getHeader("Authorization");
        try {
            ResponseEntity<?> checkResponse = authClientService.check(requestTokenHeader);

            if(checkResponse.getStatusCode().is2xxSuccessful()){
                Map<String, Object> checkResponseBody = (Map<String, Object>) checkResponse.getBody();
                isAuthenticated = checkResponseBody.getOrDefault("message", false)
                        .toString()
                        .equalsIgnoreCase("ok");
                return isAuthenticated;
            }

        } catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.error(e.getMessage());
        }

        return false;
    }
}
