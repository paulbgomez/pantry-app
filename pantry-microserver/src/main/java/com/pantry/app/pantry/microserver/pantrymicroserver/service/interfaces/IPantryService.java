package com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import java.util.List;

public interface IPantryService {

    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(String username);

    PantryDTO add(String username);

    void delete(Long id, PantryDTO pantryDTO);

    void updatePantry(Long pantryId, Long productId, Integer quantity);

    List<ProductDTO> getProductsForPantry (Long id);
}
