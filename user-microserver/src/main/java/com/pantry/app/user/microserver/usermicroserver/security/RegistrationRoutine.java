package com.pantry.app.user.microserver.usermicroserver.security;

import com.pantry.app.user.microserver.usermicroserver.clients.PantryClient;
import com.pantry.app.user.microserver.usermicroserver.controller.impl.AuthController;
import com.pantry.app.user.microserver.usermicroserver.dto.AuthenticationRequest;
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
    PantryClient pantryClient;

    public static boolean isPantryClientRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    @Scheduled(fixedRate = 10000)
    public void checkRegistrationValidation() {
            if (!isPantryClientRegistered){
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("pantry-service");
                log.info("Trying to register with pantry-service {}", dateFormat.format(new Date()));
                AuthenticationRequest authenticationRequest = new AuthenticationRequest("user-service", "user-service");
                ResponseEntity<?> responseEntity= circuitBreaker.run(() -> pantryClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("pantry-service"));
                if (responseEntity != null) {
                    parseJWTPantry(responseEntity);
                    isPantryClientRegistered = true;
                    log.info("Registered with pantry-service auth token: {}", AuthController.getPantryAuthOk());
                }
            }
    }


    private void parseJWTPantry(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        AuthController.setPantryAuthOk(auth.substring(5, auth.length() - 1));
    }


    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info( serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
    }
}
