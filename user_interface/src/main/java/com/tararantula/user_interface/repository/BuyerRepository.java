package com.tararantula.user_interface.repository;

import com.tararantula.user_interface.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
    boolean existsByName(String name);
    Buyer findByName(String name);
}
