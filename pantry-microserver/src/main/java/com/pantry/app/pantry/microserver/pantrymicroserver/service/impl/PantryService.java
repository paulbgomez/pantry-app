package com.pantry.app.pantry.microserver.pantrymicroserver.service.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PantryService implements IPantryService {

    @Autowired
    PantryRepository pantryRepository;

    @Autowired
    UserClient userClient;

    private Pantry checkPantry(Long id){
        Pantry pantry;
        if (pantryRepository.existsById(id)){
            pantry = pantryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pantry not found"));
            return pantry;
        } else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pantry not found");
        }
    }

    private UserDTO checkUser(Long id) {
        UserDTO user = userClient.getUserById(id);
        if (user.getId().equals(id)) {
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public PantryDTO getPantryById(Long id) {
        return new PantryDTO(checkPantry(id));
    }

    public List<PantryDTO> findAll(Long id) {
        UserDTO userDTO = checkUser(id);
        List<Pantry> pantries = pantryRepository.findAllByUserIdOrderByCreationDateAsc(userDTO.getId());
        List<PantryDTO> pantryDTOList = new ArrayList<>();
        for (Pantry pantry: pantries) {
            pantryDTOList.add(new PantryDTO(pantry));
        }
        return pantryDTOList;
//            /* What I need */
//            Pantry pantry;
//            Product product;
//            Integer quantityProduct;
//            Set<ProductInPantry> productsInPantry = new HashSet<>();
//
//            /* What join table brings */
//            Set<Object[]> products = pantryRepository.getProductsOfPantriesForUserId(id);
//
//
//            for (Object[] p: products) {
//                BigInteger productId = (BigInteger) p[0];
//                BigInteger pantryId = (BigInteger) p[2];
//                if(productRepository.existsById(productId.longValue()) && pantryRepository.existsById(pantryId.longValue())){
//                    product = productRepository.findById(productId.longValue()).get();
//                    pantry = pantryRepository.findById(pantryId.longValue()).get();
//                    quantityProduct = (Integer) p[1];
//                    productsInPantry.add(new ProductInPantry(pantry, product, quantityProduct));
//                }
//            }
//            for (Pantry singlePantry: pantries) {
//                singlePantry.setProductsInPantry(productsInPantry);
//            }
//
//            for (ProductInPantry x: productsInPantry){
//                System.out.println("product id: " + x.getProduct().getName() + " in pantry: " + x.getPantry().getId() + ". Total : " + x.getQuantity() );
//            }
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


