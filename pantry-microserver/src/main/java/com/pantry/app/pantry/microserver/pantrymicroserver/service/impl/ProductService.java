package com.pantry.app.pantry.microserver.pantrymicroserver.service.impl;

import com.pantry.app.pantry.microserver.pantrymicroserver.clients.UserClient;
import com.pantry.app.pantry.microserver.pantrymicroserver.controller.impl.AuthController;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.UserDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.enums.Category;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import com.pantry.app.pantry.microserver.pantrymicroserver.repository.ProductRepository;
import com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    private Product checkProduct(Long id){
       return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public ProductDTO findById(Long id) {
        return new ProductDTO(checkProduct(id));
    }

    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products) {
            productDTOList.add(new ProductDTO(product));
        }
        return productDTOList;
    }

    public List<ProductDTO> findProductByName (String nameProduct){
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> products = productRepository.findProductByNameContaining(nameProduct);
        for (Product p: products
             ) {
            productDTOList.add(new ProductDTO(p));
        }
        return productDTOList;
    }

    public ProductDTO add(ProductDTO productDTO) {
        Product product = new Product(
                productDTO.getName(),
                Category.valueOf(productDTO.getCategory()),
                productDTO.getBarcode()
        );
        productRepository.save(product);
        return new ProductDTO(product);
    }

    public void delete(Long id) {
        productRepository.delete(checkProduct(id));
    }

    public Integer getProductQuantity(Long id){
        return productRepository.getQuantityProduct(id);
    }
}
