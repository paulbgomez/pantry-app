package com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
import com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces.IPantryController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.UserDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.PantryRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.ProductRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IPantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigInteger;
import java.util.*;

@RestController
public class PantryController implements IPantryController {

    @Autowired
    IPantryService pantryService;

    @GetMapping("pantry/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PantryDTO getPantryById(@PathVariable Long id){
        return pantryService.getPantryById(id);
    }

    @GetMapping("pantry/all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PantryDTO> findAll(@PathVariable Long id){
        return pantryService.findAll(id);
    }

    @PostMapping("pantry/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PantryDTO add(@RequestBody PantryDTO pantryDTO, @PathVariable Long id){
        return pantryService.add(pantryDTO, id);
    }


    @DeleteMapping("pantry/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestBody PantryDTO pantryDTO){
        pantryService.delete(id, pantryDTO);
    }

    @PatchMapping("pantry/{pantryId}/{productId}={quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity){
       pantryService.updatePantry(pantryId, productId, quantity);
    }


}
