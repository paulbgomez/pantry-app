package com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IPantryController {

    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(String username);

    PantryDTO add(String username);

    void delete(Long id, PantryDTO pantryDTO);

    List<ProductDTO> getProductsForPantry(Long id);

    void updatePantry(Long pantryId, Long productId, Integer quantity);

    Integer getStockProductInSelectedPantry(Long productId, Long pantryId);

    void addProductToPantry(@PathVariable Long pantryId, @PathVariable Long productId);
}
