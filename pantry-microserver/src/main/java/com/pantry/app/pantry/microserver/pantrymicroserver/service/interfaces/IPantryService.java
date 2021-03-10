package com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;

import java.security.Principal;
import java.util.List;

public interface IPantryService {

    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(Principal principal);

    PantryDTO add(PantryDTO pantry, Long id);

    void delete(Long id, PantryDTO pantryDTO);

    void updatePantry(Long pantryId, Long productId, Integer quantity);
}
