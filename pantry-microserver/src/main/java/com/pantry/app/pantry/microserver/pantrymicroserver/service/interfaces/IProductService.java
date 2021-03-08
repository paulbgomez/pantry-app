package com.pantry.app.pantry.microserver.pantrymicroserver.service.interfaces;

import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IProductService {
    ProductDTO findById(Long id);

    List<ProductDTO> findAll();

    ProductDTO add(ProductDTO productDTO);

    void delete(Long id);
}
