package com.tararantula.shops.repository;

import com.tararantula.shops.entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopsRepository extends JpaRepository<Shops,Integer> {
    boolean existsByName(String name);

    Shops findByName(String name);
}
