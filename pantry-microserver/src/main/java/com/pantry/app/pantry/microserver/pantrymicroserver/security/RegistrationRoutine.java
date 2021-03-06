package com.pantry.app.pantry.microserver.pantrymicroserver.security;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
import com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl.AuthController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.AuthenticationRequest;
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

    public static boolean isPantryClientRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    @Scheduled(fixedRate = 10000)
    public void checkRegistrationValidation() {
            if (!isPantryClientRegistered){
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("user-service");
                log.info("Trying to register with user-service {}", dateFormat.format(new Date()));
                AuthenticationRequest authenticationRequest = new AuthenticationRequest("pantry-service", "pantry-service");
                ResponseEntity<?> responseEntity= circuitBreaker.run(() -> userClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("user-service"));
                if (responseEntity != null) {
                    parseJWTUser(responseEntity);
                    isPantryClientRegistered = true;
                    log.info("Registered with user-service auth token: {}", AuthController.getUserAuthOk());
                }
            }
    }


    private void parseJWTUser(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        AuthController.setUserAuthOk(auth.substring(5, auth.length() - 1));
    }


    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info( serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
    }
}
