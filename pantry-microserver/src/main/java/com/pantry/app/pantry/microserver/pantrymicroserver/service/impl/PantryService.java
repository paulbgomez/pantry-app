package com.pantry.app.pantry.microserver.pantrymicroserver.service.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
import com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl.AuthController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.PantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductInPantryDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.UserDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.PantryRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.ProductInPantryRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.ProductRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IPantryService;
import com.pantry.app.pantry.microserver.pantrymicroserver.utils.CompositeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
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

    @Autowired
    ProductInPantryRepository productInPantryRepository;

    private Pantry checkPantry(Long id) {
        return pantryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pantry not found"));
    }

    private UserDTO createUserWithAuth(String username){
        return userClient.getUserByUsername(
                username, "Bearer " + AuthController.getUserAuthOk());
    }

    private Product checkProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This product doesn't exist in our database."));
    }

    public PantryDTO getPantryById(Long id) {
        return new PantryDTO(checkPantry(id));
    }

    /** FIND ALL PANTRIES FOR AN USER BY USERNAME **/
    public List<PantryDTO> findAll(String username) {
        UserDTO userDTO = createUserWithAuth(username);
        List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(userDTO.getId());
        List<PantryDTO> pantryDTOList = new ArrayList<>();
        for (Pantry pantry: pantries) {
            pantryDTOList.add(new PantryDTO(pantry));
        }
        return pantryDTOList;
    }

    /** ADD ONE PANTRY FOR AN USER BY USERNAME **/
    public PantryDTO add(String username) {
        UserDTO userDTO = createUserWithAuth(username);
        Pantry pantry = pantryRepository.save(new Pantry(
                "Pantry of " + username,
                userDTO.getId()
        ));
        return new PantryDTO(pantry);
    }

    public void delete(String username, Long id) {
        UserDTO userDTO = createUserWithAuth(username);
        List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(userDTO.getId());
        Pantry pantry = checkPantry(id);
        if(pantries.contains(pantry)){
            pantryRepository.delete(pantry);
        }
    }

    public void deleteProductPantry(String username, Long pantryId, Long productId) {
        createUserWithAuth(username);
        checkPantry(pantryId);
        checkProduct(productId);
        CompositeKey compositeKey = new CompositeKey(pantryId, productId);
        ProductInPantry productInPantry = productInPantryRepository.findById(compositeKey).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE ,"This pantry doesn't have that product to delete"));
        productInPantryRepository.delete(productInPantry);
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
        Product product = checkProduct(productId);

        CompositeKey compositeKey = new CompositeKey(pantryId, productId);

        ProductInPantry productInPantry = new ProductInPantry(
                compositeKey,
                pantry,
                product,
                1
        );

        if(!pantry.getProductsInPantry().contains(productInPantry)){
            pantry.getProductsInPantry().add(productInPantry);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
        }
        productInPantryRepository.save(productInPantry);
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


