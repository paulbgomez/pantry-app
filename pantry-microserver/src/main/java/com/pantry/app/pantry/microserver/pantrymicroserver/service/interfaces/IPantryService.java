package com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IPantryService {

    PantryDTO getPantryById(Long id);

    List<PantryDTO> findAll(Long id);

    PantryDTO add(PantryDTO pantry, Long id);

    void delete(Long id, PantryDTO pantry);

    void updatePantry(Long pantryId, Long productId, Integer quantity);
}
