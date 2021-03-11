package com.pantry.app.edgeservice.controller.impl;

import com.pantry.app.edgeservice.clients.PantryClient;
import com.pantry.app.edgeservice.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    PantryClient pantryClient;

    private static String pantryAuthOk;

    @GetMapping("/product/{id}")
    public ProductDTO findProductById(@PathVariable Long id){
        return pantryClient.findProductById(id, "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/product")
    public List<ProductDTO> findAllProducts(){
        return pantryClient.findAllProducts("Bearer " + getPantryAuthOk());
    }

    @PostMapping("/product")
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO){
        return pantryClient.addProduct(productDTO, "Bearer " + getPantryAuthOk());
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        pantryClient.deleteProduct(id, "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/product/stock/{id}")
    public Integer getProductQuantity(@PathVariable Long id) {
        return pantryClient.getProductQuantity(id, "Bearer " + getPantryAuthOk());
    }

    public static String getPantryAuthOk() {
        return pantryAuthOk;
    }

    public static void setPantryAuthOk(String pantryAuthOk) {
        ProductController.pantryAuthOk = pantryAuthOk;
    }
}
