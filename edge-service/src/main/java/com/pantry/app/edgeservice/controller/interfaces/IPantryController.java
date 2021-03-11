package com.pantry.app.edgeservice.controller.interfaces;

import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.ProductDTO;

import java.security.Principal;
import java.util.List;

public interface IPantryController {
    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(Principal principal);

    List<ProductDTO> getProductsForPantry(Long id);

    PantryDTO add(Principal principal);

    void delete(Long id, PantryDTO pantryDTO);

    void updatePantry(Long pantryId, Long productId, Integer quantity);
}
