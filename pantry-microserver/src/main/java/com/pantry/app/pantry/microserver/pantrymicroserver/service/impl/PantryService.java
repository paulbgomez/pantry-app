package com.pantry.app.pantry.microserver.pantrymicroserver.service.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
import com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl.AuthController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.UserDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.PantryRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.ProductRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IPantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PantryService implements IPantryService {

    @Autowired
    PantryRepository pantryRepository;

    @Autowired
    UserClient userClient;

    @Autowired
    ProductRepository productRepository;

    private Pantry checkPantry(Long id) {
        return pantryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pantry not found"));
    }

    private UserDTO checkUserUsername(String username) {
        return userClient.getUserByUsername(username, "Bearer " + AuthController.getUserAuthOk());
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

    /** FIND ALL PANTRIES FOR AN USER BY USERNAME **/
    public List<PantryDTO> findAll(String username) {
        UserDTO userDTO = userClient.getUserByUsername(
                username, "Bearer " + AuthController.getUserAuthOk());
        List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(userDTO.getId());
        List<PantryDTO> pantryDTOList = new ArrayList<>();
        for (Pantry pantry: pantries) {
            pantryDTOList.add(new PantryDTO(pantry));
        }
        return pantryDTOList;
    }

    /** ADD ONE PANTRY FOR AN USER BY USERNAME **/
    public PantryDTO add(String username) {
        UserDTO userDTO = userClient.getUserByUsername(
                username, "Bearer " + AuthController.getUserAuthOk());
        Pantry pantry = pantryRepository.save(new Pantry(
                "Pantry of " + username,
                userDTO.getId()
        ));
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

    public void addProductToPantry(Long pantryId, Long productId) {
        Pantry pantry = checkPantry(pantryId);
        for (ProductInPantry product: pantry.getProductsInPantry()){
            if(product.getProduct().getId().equals(productId)){
                product.setQuantity(1);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
            }
        }
        pantryRepository.save(pantry);
    }

    public List<ProductDTO> getProductsForPantry(Long id) {
        Set<Object[]> productsFromPantry =  pantryRepository.getProductsForPantryId(id);
        List<ProductDTO> products = new ArrayList<>();

        for(Object[] object: productsFromPantry){
            BigInteger productId = (BigInteger) object[0];
            Product product = productRepository.findById(productId.longValue()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
            products.add(new ProductDTO(product));
        }

        return products;
    }

    public Integer getStockProductInSelectedPantry(Long productId, Long pantryId){
        return pantryRepository.getStockProductInAPantry(productId, pantryId);
    }
}


