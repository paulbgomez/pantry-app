package com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;

import java.util.List;

public interface IProductController {
    ProductDTO findProductById(Long id);

    List<ProductDTO> findAllProducts();

    ProductDTO addProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

    Integer getProductQuantity(Long id);
}
