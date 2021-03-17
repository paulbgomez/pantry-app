package com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces.IProductController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController implements IProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO findProductById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/product/name={productName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findByName(@PathVariable String productName){
        return productService.findProductByName(productName);
    }

    @GetMapping("/product/stock/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getProductQuantity(@PathVariable Long id) {
        return productService.getProductQuantity(id);
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO){
        return productService.add(productDTO);
    }

    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }

}
