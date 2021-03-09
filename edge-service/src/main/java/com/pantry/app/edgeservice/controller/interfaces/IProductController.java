package com.pantry.app.edgeservice.controller.interfaces;

import com.pantry.app.edgeservice.dto.ProductDTO;

import java.util.List;

public interface IProductController {

    ProductDTO findProductById(Long id);

    List<ProductDTO> findAllProducts();

    ProductDTO addProduct(ProductDTO productDTO);

    void deleteProduct(Long id);
}
