package com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces.IPantryController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductInPantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IPantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PantryController implements IPantryController {

    @Autowired
    IPantryService pantryService;

    /** @GET PANTRY BY ID **/
    @GetMapping("/pantry/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PantryDTO getPantryById(@PathVariable Long id){
        return pantryService.getPantryById(id);
    }

    /** @GET ALL PANTRY FOR A USER **/
    @GetMapping("/pantry/all/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<PantryDTO> findAll(@PathVariable String username){
        return pantryService.findAll(username);
    }

    /** @GET ALL PRODUCTS FOR A PANTRY **/
    @GetMapping("/pantry/all/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductsForPantry(@PathVariable Long id){
        return pantryService.getProductsForPantry(id);
    }

    /** @GET STOCK FOR A PRODUCT IN A PANTRY **/
    @GetMapping("/pantry/{pantryId}/stock/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getStockProductInSelectedPantry(@PathVariable Long productId,@PathVariable Long pantryId){
        return pantryService.getStockProductInSelectedPantry(productId, pantryId);
    }

    /** @POST PRODUCT STOCK IN A PANTRY **/
    @PostMapping("/pantry/add/{pantryId}/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToPantry(@PathVariable Long pantryId, @PathVariable Long productId){
        pantryService.addProductToPantry(pantryId, productId);
    }

    /** @POST A PANTRY **/
    @PostMapping("/pantry/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public PantryDTO add(@PathVariable String username){
        return pantryService.add(username);
    }

    /** @PATCH PRODUCT STOCK IN A PANTRY **/
    @PatchMapping("/pantry/{pantryId}/{productId}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity){
        pantryService.updatePantry(pantryId, productId, quantity);
    }

    /** @DELETE A PANTRY FOR AN ID **/
    @DeleteMapping("/pantry/{id}/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String username,@PathVariable Long id){
        pantryService.delete(username, id);
    }

    /** @DELETE A PRODUCT FROM A PANTRY **/
    @DeleteMapping("/pantry/{pantryId}/product/{productId}/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductPantry(@PathVariable String username,@PathVariable Long pantryId,@PathVariable Long productId){
        pantryService.deleteProductPantry(username, pantryId, productId);
    }

}
