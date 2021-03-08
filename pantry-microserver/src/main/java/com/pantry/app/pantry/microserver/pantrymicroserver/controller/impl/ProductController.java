package com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces.IProductController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.ProductRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProductController implements IProductController {

    @Autowired
    IProductService productService;

    @GetMapping("product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("product")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> findAll(){
        return productService.findAll();
    }

    @PostMapping("product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO add(@RequestBody ProductDTO productDTO){
        return productService.add(productDTO);
    }

    @DeleteMapping("product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}
