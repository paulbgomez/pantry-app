package com.pantry.app.edgeservice.controller.impl;

import com.pantry.app.edgeservice.clients.PantryClient;
import com.pantry.app.edgeservice.controller.interfaces.IPantryController;
import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.ProductDTO;
import com.pantry.app.edgeservice.dto.ProductInPantryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class PantryController implements IPantryController {

    @Autowired
    PantryClient pantryClient;

    private static String pantryAuthOk;

    /**
    @PANTRY ENDPOINTS
     **/

    /*
    GET REQUESTS
     */

    @GetMapping("/pantry/{id}")
    public PantryDTO getPantryById(@PathVariable Long id){
        return pantryClient.getPantryById(id, "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/pantry/all")
    public List<PantryDTO> findAll(Principal principal){
        return pantryClient.findAll(principal.getName(), "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/pantry/all/products/{id}")
    public List<ProductDTO> getProductsForPantry(@PathVariable Long id) {
        return pantryClient.getProductsForPantry(id, "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/pantry/{pantryId}/stock/product/{productId}")
    public Integer getStockProductInSelectedPantry(@PathVariable Long productId,@PathVariable Long pantryId){
        return pantryClient.getStockProductInSelectedPantry(productId, pantryId, "Bearer " + getPantryAuthOk());
    }

    /*
    POST REQUESTS
     */

    @PostMapping("/pantry")
    public PantryDTO add(Principal principal){
        return pantryClient.add(principal.getName(), "Bearer " + getPantryAuthOk());
    }

    @PostMapping("/pantry/add/{pantryId}/{productId}")
    public void addProductToPantry(@PathVariable Long pantryId, @PathVariable Long productId){
        pantryClient.addProductToPantry(pantryId, productId, "Bearer " + getPantryAuthOk());
    }

    /*
    PATCH/PUT REQUESTS
     */

    @PatchMapping("/pantry/{pantryId}/{productId}/{quantity}")
    public void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity){
        pantryClient.updatePantry(pantryId, productId, quantity, "Bearer " + getPantryAuthOk());
    }

    @PatchMapping("new/pantry/name/{pantryId}/{newName}")
    public PantryDTO editPantryName(@PathVariable Long pantryId,@PathVariable String newName){
        return pantryClient.editPantryName(pantryId, newName, "Bearer " + getPantryAuthOk());
    }

    /*
    DELETE REQUESTS
     */

    /** @DELETE A PANTRY FOR AN ID **/
    @DeleteMapping("/pantry/{id}")
    public void delete(Principal principal, @PathVariable Long id){
        pantryClient.delete(principal.getName(), id, "Bearer " + getPantryAuthOk());
    }

    /** @DELETE A PRODUCT FROM A PANTRY **/
    @DeleteMapping("/pantry/{pantryId}/product/{productId}")
    public void deleteProductPantry(Principal principal, @PathVariable Long pantryId,@PathVariable Long productId){
        pantryClient.deleteProductPantry(principal.getName(), pantryId, productId, "Bearer " + getPantryAuthOk());
    }

    /**
     @PRODUCT ENDPOINTS
     **/

        /*
    GET REQUESTS
     */

    @GetMapping("/product/{id}")
    public ProductDTO findProductById(@PathVariable Long id){
        return pantryClient.findProductById(id, "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/product")
    public List<ProductDTO> findAllProducts(){
        return pantryClient.findAllProducts("Bearer " + getPantryAuthOk());
    }

    @GetMapping("/product/stock/{id}")
    public Integer getProductQuantity(@PathVariable Long id) {
        return pantryClient.getProductQuantity(id, "Bearer " + getPantryAuthOk());
    }


    @GetMapping("/product/name={productName}")
    public List<ProductDTO> findByName(@PathVariable String productName){
        return pantryClient.findByName(productName, "Bearer " + getPantryAuthOk());
    };


    /*
    POST REQUESTS
    */

    @PostMapping("/product")
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO){
        return pantryClient.addProduct(productDTO, "Bearer " + getPantryAuthOk());
    }

    /*
    DELETE REQUESTS
    */

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        pantryClient.deleteProduct(id, "Bearer " + getPantryAuthOk());
    }


    public static String getPantryAuthOk() {
        return pantryAuthOk;
    }

    public static void setPantryAuthOk(String pantryAuthOk) {
        PantryController.pantryAuthOk = pantryAuthOk;
    }
}
