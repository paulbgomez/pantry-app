package com.pantry.app.user.microserver.usermicroserver.clients;

import com.pantry.app.user.microserver.usermicroserver.dto.AuthenticationRequest;
import com.pantry.app.user.microserver.usermicroserver.dto.PantryDTO;
import com.pantry.app.user.microserver.usermicroserver.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@FeignClient("pantry-service")
public interface PantryClient {

    @PostMapping("/pantry/{id}")
    PantryDTO add(@RequestBody @Valid PantryDTO pantryDTO, @PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/pantry/all")
    List<PantryDTO> findAll(Principal principal, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/pantry/{id}")
    PantryDTO getPantryById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/pantry/{id}")
    void delete(@PathVariable Long id, @RequestBody @Valid PantryDTO pantryDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PatchMapping("/pantry/{pantryId}/{productId}/{quantity}")
    void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/product/{id}")
    ProductDTO findProductById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/product")
    List<ProductDTO> findAllProducts(@RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/product")
    ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/pantry/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

}
