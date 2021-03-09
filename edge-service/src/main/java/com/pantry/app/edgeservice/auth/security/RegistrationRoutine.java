package com.pantry.app.edgeservice.auth.security;

import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.controller.impl.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class RegistrationRoutine {
    @Autowired
    UserClient userClient;

    public static boolean isUserRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    @Scheduled(fixedRate = 10000)
    public void checkUserRegistration() {
        if (!isUserRegistered) {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
            log.info("Trying to register with user-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("gateway", "gateway");
            ResponseEntity<?> responseEntity = circuitBreaker.run(() -> userClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("user-service"));
            if (responseEntity != null) {
                parseJWT(responseEntity);
                isUserRegistered = true;
                log.info("Registered with user-service auth token: {}", AuthController.getUserAuthOk());
            }
        }
    }

    private void parseJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        AuthController.setUserAuthOk(auth.substring(5, auth.length() - 1));
        }

    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info(serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
        }
}