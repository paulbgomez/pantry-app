package com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;

import java.util.List;

public interface IPantryController {

    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(Long id);

    PantryDTO add(PantryDTO pantry, Long id);

    void delete(Long id, PantryDTO pantryDTO);

    void updatePantry(Long pantryId, Long productId, Integer quantity);
}
