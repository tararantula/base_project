package com.tararantula.warehouses_factories.controllers;

import com.tararantula.warehouses_factories.Repository.WarehousesRepository;
import com.tararantula.warehouses_factories.entity.Warehouses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/warehousesAndFactories")
public class MainController {
    WarehousesRepository warehousesRepository;
    //добавть продукт
    @GetMapping("/addProduct")
    public Warehouses addProduct(@RequestParam String nameWarehouses, @RequestParam String nameProduct, @RequestParam String description, @RequestParam int number) {
        warehousesRepository.save(Warehouses.builder().nameWarehouse(nameWarehouses).nameProduct(nameProduct).description(description).number(number).build());
        return warehousesRepository.findByName(nameWarehouses);
    }
    //удаление продукта
    @DeleteMapping("/DeleteProduct")
    public void DeleteProduct(@PathVariable Integer id){
        warehousesRepository.deleteById(id);
    }
    //пополнение магазина со склада
    @PostMapping("/productDelivery/{idProductFromWarehouse}&{number}&{name}&{description}")
    public ResponseEntity<?> productDelivery(@PathVariable Integer idProductFromWarehouse, @PathVariable int number, @PathVariable String name, @PathVariable String description) {
        Warehouses warehouses = warehousesRepository.findById(idProductFromWarehouse).get();
        if (warehouses.getNumber() > number && warehouses.getNameProduct().equals(name) && warehouses.getDescription().equals(description)) {
            warehouses.setNumber(warehouses.getNumber() - number);
            warehousesRepository.save(warehouses);
            return ResponseEntity.ok().build();
        }
            return ResponseEntity.status(400).build();
    }
}

