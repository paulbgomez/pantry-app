package com.pantry.app.edgeservice.controller.impl;

import com.pantry.app.edgeservice.clients.PantryClient;
import com.pantry.app.edgeservice.controller.interfaces.IPantryController;
import com.pantry.app.edgeservice.dto.PantryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class PantryController implements IPantryController {

    @Autowired
    PantryClient pantryClient;

    private static String pantryAuthOk;

    @PostMapping("/pantry/{id}")
    public PantryDTO add(@RequestBody PantryDTO pantryDTO, @PathVariable Long id){
        return pantryClient.add(pantryDTO, id, "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/pantry/{id}")
    public PantryDTO getPantryById(@PathVariable Long id){
        return pantryClient.getPantryById(id, "Bearer " + getPantryAuthOk());
    }

    @GetMapping("/pantry/all")
    public List<PantryDTO> findAll(Principal principal){
        System.out.println("Edge service pantry controller line 32 " + principal.getName());
        return pantryClient.findAll(principal.getName(), "Bearer " + getPantryAuthOk());
    }

    @DeleteMapping("/pantry/{id}")
    public void delete(@PathVariable Long id, @RequestBody @Valid PantryDTO pantryDTO){
        pantryClient.delete(id, pantryDTO, "Bearer " + getPantryAuthOk());
    }

    @PatchMapping("/pantry/{pantryId}/{productId}/{quantity}")
    public void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity){
        pantryClient.updatePantry(pantryId, productId, quantity, "Bearer " + getPantryAuthOk());
    }

    public static String getPantryAuthOk() {
        return pantryAuthOk;
    }

    public static void setPantryAuthOk(String pantryAuthOk) {
        PantryController.pantryAuthOk = pantryAuthOk;
    }
}