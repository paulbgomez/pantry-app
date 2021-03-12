package com.pantry.app.edgeservice.controller.interfaces;

import com.pantry.app.edgeservice.dto.PantryDTO;
import com.pantry.app.edgeservice.dto.ProductDTO;
import com.pantry.app.edgeservice.dto.ProductInPantryDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

public interface IPantryController {
    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(Principal principal);

    List<ProductDTO> getProductsForPantry(Long id);

    PantryDTO add(Principal principal);

    void delete(Principal principal, Long id);

    void updatePantry(Long pantryId, Long productId, Integer quantity);

    Integer getStockProductInSelectedPantry(Long productId, Long pantryId);

    void addProductToPantry(Long pantryId, Long productId);

    void deleteProductPantry(Principal principal, Long pantryId, Long productId);

}
