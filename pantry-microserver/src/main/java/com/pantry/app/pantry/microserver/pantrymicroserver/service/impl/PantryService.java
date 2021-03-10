package com.pantry.app.pantry.microserver.pantrymicroserver.service.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
import com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl.AuthController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.UserDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.PantryRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IPantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PantryService implements IPantryService {

    @Autowired
    PantryRepository pantryRepository;

    @Autowired
    UserClient userClient;

    private Pantry checkPantry(Long id) {
        return pantryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pantry not found"));
    }

    private UserDTO checkUser(Long id) {
        UserDTO user = userClient.getUserById(id, "Bearer " + AuthController.getUserAuthOk());
        if (user.getId().equals(id)) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public PantryDTO getPantryById(Long id) {
        return new PantryDTO(checkPantry(id));
    }

    public List<PantryDTO> findAll(Principal principal) {
//        UserDTO userDTO = checkUser(id);
        UserDTO userDTO = userClient.getUserByUsername(
                principal.getName(), "Bearer " + AuthController.getUserAuthOk());

        List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(userDTO.getId());
        List<PantryDTO> pantryDTOList = new ArrayList<>();
        for (Pantry pantry: pantries) {
            pantryDTOList.add(new PantryDTO(pantry));
        }
        return pantryDTOList;
    }

    public PantryDTO add(PantryDTO pantryDTO, Long id) {
        UserDTO userDTO = checkUser(id);
        Pantry pantry = new Pantry(
                pantryDTO.getName(),
                userDTO.getId()
        );
        pantryRepository.save(pantry);
        return new PantryDTO(pantry);
    }

    public void delete(Long id, PantryDTO pantryDTO) {
        UserDTO userDTO = checkUser(id);
        List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(userDTO.getId());
        pantryRepository.delete(Objects.requireNonNull(pantries.stream().filter(item -> item.getName().equals(pantryDTO.getName()))
                .findAny().orElse(null)));
    }

    public void updatePantry(Long pantryId, Long productId, Integer quantity) {
        Pantry pantry = checkPantry(pantryId);
        for (ProductInPantry product: pantry.getProductsInPantry()){
            if(product.getProduct().getId().equals(productId)){
                product.setQuantity(quantity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
            }
        }
        pantryRepository.save(pantry);
    }

}


