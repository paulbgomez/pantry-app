package com.pantry.app.pantry.microserver.pantrymicroserver.service.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.PantryRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IPantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PantryService implements IPantryService {

    @Autowired
    PantryRepository pantryRepository;

    private Pantry checkPantry(Long id){
        Pantry pantry = new Pantry();
        if (pantryRepository.existsById(id)){
            pantry = pantryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pantry not found"));
        }
        return pantry;
    }

    public PantryDTO getPantryById(Long id) {
        return new PantryDTO(checkPantry(id));
    }

    @Override
    public List<PantryDTO> findAll(Long id) {
        
    }

    @Override
    public PantryDTO add(PantryDTO pantry, Long id) {
    }

    @Override
    public void delete(Long id, PantryDTO pantry) {

    }

    @Override
    public void updatePantry(Long pantryId, Long productId, Integer quantity) {

    }
}
