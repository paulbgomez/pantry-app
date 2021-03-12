package com.pantry.app.edgeservice.clients;

import com.pantry.app.edgeservice.auth.security.AuthenticationRequest;
import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("pantry-service")
public interface PantryClient {

    @PostMapping("/pantry/{username}")
    PantryDTO add(@PathVariable String username, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/pantry/all/{username}")
    List<PantryDTO> findAll(@PathVariable String username, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/pantry/all/products/{id}")
    List<ProductDTO> getProductsForPantry(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/pantry/{pantryId}/stock/product/{productId}")
    Integer getStockProductInSelectedPantry(@PathVariable Long productId,@PathVariable Long pantryId, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/pantry/{id}")
    PantryDTO getPantryById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/pantry/{id}/{username}")
    void delete(@PathVariable String username , @PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/pantry/{pantryId}/product/{productId}/{username}")
    void deleteProductPantry(@PathVariable String username , @PathVariable Long pantryId, @PathVariable Long productId, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PatchMapping("/pantry/{pantryId}/{productId}/{quantity}")
    void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/product/{id}")
    ProductDTO findProductById(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/product")
    List<ProductDTO> findAllProducts(@RequestHeader(value = "Authorization") String authorizationHeader);

    /** @POST PRODUCT STOCK IN A PANTRY **/
    @PostMapping("/pantry/add/{pantryId}/{productId}")
    void addProductToPantry(@PathVariable Long pantryId, @PathVariable Long productId, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/product")
    ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/product/stock/{id}")
    Integer getProductQuantity(@PathVariable Long id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/pantry/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

}
