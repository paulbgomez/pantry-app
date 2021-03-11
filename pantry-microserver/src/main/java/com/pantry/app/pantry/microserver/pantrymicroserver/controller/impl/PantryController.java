package com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces.IPantryController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IPantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class PantryController implements IPantryController {

    @Autowired
    IPantryService pantryService;

    @GetMapping("/pantry/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PantryDTO getPantryById(@PathVariable Long id){
        return pantryService.getPantryById(id);
    }

    @GetMapping("/pantry/all/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<PantryDTO> findAll(@PathVariable String username){
        return pantryService.findAll(username);
    }

    @GetMapping("/pantry/all/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductsForPantry(@PathVariable Long id){
        return pantryService.getProductsForPantry(id);
    }

    @PostMapping("/pantry/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public PantryDTO add(@PathVariable String username){
        return pantryService.add(username);
    }

    @DeleteMapping("/pantry/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestBody @Valid PantryDTO pantryDTO){
        pantryService.delete(id, pantryDTO);
    }

    @PatchMapping("/pantry/{pantryId}/{productId}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity){
       pantryService.updatePantry(pantryId, productId, quantity);
    }

    @GetMapping("/pantry/{pantryId}/stock/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getStockProductInSelectedPantry(@PathVariable Long productId,@PathVariable Long pantryId){
        return pantryService.getStockProductInSelectedPantry(productId, pantryId);
    }

}
