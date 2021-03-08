package com.pantry.app.pantry.microserver.pantrymicroserver.controller.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;

import java.util.List;

public interface IProductController {
    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    ProductDTO add(ProductDTO productDTO);

    void delete(Long id);
}
