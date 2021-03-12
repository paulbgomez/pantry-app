package com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductInPantryDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IPantryController {

    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(String username);

    PantryDTO add(String username);

    void delete(String username, Long id);

    List<ProductDTO> getProductsForPantry(Long id);

    void updatePantry(Long pantryId, Long productId, Integer quantity);

    Integer getStockProductInSelectedPantry(Long productId, Long pantryId);

    void addProductToPantry(Long pantryId, Long productId);

    void deleteProductPantry(String username, Long pantryId, Long productId);
}
