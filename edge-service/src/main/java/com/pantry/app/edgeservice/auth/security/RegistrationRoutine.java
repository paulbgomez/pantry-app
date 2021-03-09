package com.pantry.app.edgeservice.auth.security;

import com.pantry.app.edgeservice.clients.PantryClient;
import com.pantry.app.edgeservice.clients.UserClient;
import com.pantry.app.edgeservice.controller.impl.AuthController;
import com.pantry.app.edgeservice.controller.impl.PantryController;
import com.pantry.app.edgeservice.controller.impl.UserController;
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

    @Autowired
    PantryClient pantryClient;

    public static boolean isUserRegistered = false;
    public static boolean isPantryRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    @Scheduled(fixedRate = 10000)
    public void checkUserRegistration() {
        if (!isUserRegistered) {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
            log.info("Trying to register with user-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("edge-service", "edge-service");
            ResponseEntity<?> responseEntity = userClient.createAuthenticationToken(authenticationRequest);
            if (responseEntity != null) {
                parseJWT(responseEntity);
                isUserRegistered = true;
                log.info("Registered with user-service auth token: {}", UserController.getUserAuthOk());
            }
        }
    }

    private void parseJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        UserController.setUserAuthOk(auth.substring(5, auth.length() - 1));
        }

    @Scheduled(fixedRate = 10000)
    public void checkPantryRegistration() {
        if (!isPantryRegistered) {
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("pantry-service");
            log.info("Trying to register with pantry-service {}", dateFormat.format(new Date()));
            AuthenticationRequest authenticationRequest = new AuthenticationRequest("edge-service", "edge-service");
            ResponseEntity<?> responseEntity = pantryClient.createAuthenticationToken(authenticationRequest);
            if (responseEntity != null) {
                parseJWTPantry(responseEntity);
                isPantryRegistered = true;
                log.info("Registered with pantry-service auth token: {}", PantryController.getPantryAuthOk());
            }
        }
    }

    private void parseJWTPantry(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        PantryController.setPantryAuthOk(auth.substring(5, auth.length() - 1));
    }

    private ResponseEntity<?> fallbackTransaction() {
        log.info("user-service" + " is not reachable {}", dateFormat.format(new Date()));
        return null;
        }
}