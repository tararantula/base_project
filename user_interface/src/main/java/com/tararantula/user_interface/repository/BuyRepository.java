package com.tararantula.user_interface.repository;

import com.tararantula.user_interface.entity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Integer> {
    Buy findByBuyingDate(Date buyingDate);
    List<Buy> findAllByBuyerId(Integer id);
}

