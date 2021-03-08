package com.pantry.app.pantry.microserver.pantrymicroserver.repository;

import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
