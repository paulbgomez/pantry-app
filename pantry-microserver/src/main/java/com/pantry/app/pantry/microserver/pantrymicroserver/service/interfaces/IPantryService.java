package com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductInPantryDTO;

import java.util.List;

public interface IPantryService {

    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(String username);

    PantryDTO add(String username);

    void delete(String username, Long id);

    void updatePantry(Long pantryId, Long productId, Integer quantity);

    List<ProductDTO> getProductsForPantry (Long id);

    Integer getStockProductInSelectedPantry(Long productId, Long pantryId);

    void addProductToPantry(Long pantryId, Long productId);

    void deleteProductPantry(String username, Long pantryId, Long productId);

    PantryDTO editPantryName(Long pantryId, String newName);
}
