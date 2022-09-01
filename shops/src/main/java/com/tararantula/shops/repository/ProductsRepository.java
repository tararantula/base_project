package com.tararantula.shops.repository;

import com.tararantula.shops.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
    boolean existsByShopsId(Integer shopsId);

}
