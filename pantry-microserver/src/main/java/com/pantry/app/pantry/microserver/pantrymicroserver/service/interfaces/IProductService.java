package com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    ProductDTO add(ProductDTO productDTO);

    void delete(Long id);
}
