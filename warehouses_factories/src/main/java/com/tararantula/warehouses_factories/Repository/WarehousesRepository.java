package com.tararantula.warehouses_factories.Repository;

import com.tararantula.warehouses_factories.entity.Warehouses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehousesRepository extends JpaRepository<Warehouses,Integer> {
    Warehouses findByName(String nameWarehouse);
}
