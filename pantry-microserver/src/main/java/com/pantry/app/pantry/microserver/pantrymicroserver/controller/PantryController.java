package com.pantry.app.pantry.microserver.pantrymicroserver.controller;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.UserDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.PantryRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigInteger;
import java.util.*;

@RestController
public class PantryController {

    @Autowired
    PantryRepository pantryRepository;

    @Autowired
    UserClient userClient;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("pantry/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pantry getPantryById(@PathVariable Long id){
        if (pantryRepository.existsById(id)){
            Pantry pantry = pantryRepository.findById(id).get();
            for (ProductInPantry x: pantry.getProductsInPantry()){
                System.out.println("product id: " + x.getProduct().getName() + " in pantry: " + x.getPantry().getId() + ". Total : " + x.getQuantity() );
            };
            return pantry;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
        }
    }

    @GetMapping("pantry/all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Pantry> findAll(@PathVariable Long id){
        UserDTO user = userClient.getUserById(id);
        if (user.getId().equals(id)){

            /* What I need */
            Pantry pantry;
            Product product;
            Integer quantityProduct;
            Set<ProductInPantry> productsInPantry = new HashSet<>();

            /* What join table brings */
            Set<Object[]> products = pantryRepository.getProductsOfPantriesForUserId(id);


            for (Object[] p: products) {
                BigInteger productId = (BigInteger) p[0];
                BigInteger pantryId = (BigInteger) p[2];
                if(productRepository.existsById(productId.longValue()) && pantryRepository.existsById(pantryId.longValue())){
                    product = productRepository.findById(productId.longValue()).get();
                    pantry = pantryRepository.findById(pantryId.longValue()).get();
                    quantityProduct = (Integer) p[1];
                    productsInPantry.add(new ProductInPantry(pantry, product, quantityProduct));
                }
            }
            List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(id);

            for (Pantry singlePantry: pantries) {
                singlePantry.setProductsInPantry(productsInPantry);
            }

            for (ProductInPantry x: productsInPantry){
                System.out.println("product id: " + x.getProduct().getName() + " in pantry: " + x.getPantry().getId() + ". Total : " + x.getQuantity() );
            }

            return pantries;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }

    @PostMapping("pantry/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pantry add(@RequestBody Pantry pantry, @PathVariable Long id){
        UserDTO user = userClient.getUserById(id);
       if (user.getId().equals(id)){
           Pantry pantryModel = new Pantry(
                   pantry.getName(),
                   pantry.getUserId()
           );
           pantryModel.setUserId(id);
           return pantryRepository.save(pantryModel);
       } else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
       }
    }


    @DeleteMapping("pantry/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestBody Pantry pantry){
        UserDTO user = userClient.getUserById(id);
        if (user.getId().equals(id)){
            List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(id);
            pantryRepository.delete(Objects.requireNonNull(pantries.stream().filter(item -> item.getName().equals(pantry.getName()))
                    .findAny().orElse(null)));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }

    @PatchMapping("pantry/{pantryId}/{productId}={quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePantry(@PathVariable Long pantryId, @PathVariable Long productId, @PathVariable Integer quantity){
        if (pantryRepository.existsById(pantryId)){
            Pantry pantry = pantryRepository.findById(pantryId).get();
            for (ProductInPantry x: pantry.getProductsInPantry()){
               if(x.getProduct().getId().equals(productId)){
                   x.setQuantity(quantity);
               }
            };
            pantryRepository.save(pantry);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
        }
    }


}
