package com.tararantula.shops.controllers;

import com.tararantula.shops.entity.BuyInfo;
import com.tararantula.shops.entity.Products;
import com.tararantula.shops.entity.Shops;
import com.tararantula.shops.repository.BuyInfoRepository;
import com.tararantula.shops.repository.ProductsRepository;
import com.tararantula.shops.repository.ShopsRepository;
import com.tararantula.shops.service.RestTemplateServise;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/shop")
public class MainController {
    ProductsRepository productsRepository;
    ShopsRepository shopsRepository;
    BuyInfoRepository buyInfoRepository;
    RestTemplateServise restTemplateServise;
    //создание продукта
    @GetMapping("/createProducts")
    public ResponseEntity<?> createProducts(@RequestParam String name, @RequestParam String description, @RequestParam int sum, @RequestParam int number, @RequestParam Integer shopsId) {
        if (shopsRepository.existsById(shopsId)) {
            productsRepository.save(Products.builder().name(name).description(description).sum(sum).number(number).shopsId(shopsId).build());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }
    //удаление продукта
    @DeleteMapping("/deleteProducts")
    public void deleteProducts(@PathVariable Integer id){
        productsRepository.deleteById(id);
    }
    //создание магазина
    @GetMapping("/createShops")
    public Shops createShops(@RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
        shopsRepository.save(Shops.builder().name(name).address(address).phoneNumber(phoneNumber).build());
        return shopsRepository.findByName(name);
    }
    //удаление магазина
    @DeleteMapping("/deleteShop")
    public void deleteShop(@PathVariable Integer id){
        if (!productsRepository.existsByShopsId(id)) {
            shopsRepository.deleteById(id);
        }
    }
    //получение списка мазазинов
    @GetMapping("/getShops")
    public List<Shops> getShops() {
        return shopsRepository.findAll();
    }

    //создание покупки
    @PostMapping("/createBuy/{nameShops}&{id}&{amount}&{nameBuyer}&{paymentMethod}")
    public void createBuy(@PathVariable String nameShops,@PathVariable Integer id,@PathVariable int amount,@PathVariable String nameBuyer,@PathVariable String paymentMethod) {
       if(shopsRepository.existsByName(nameShops) && productsRepository.existsById(id) && productsRepository.findById(id).get().getShopsId() == shopsRepository.findByName(nameShops).getId() && amount <= productsRepository.findById(id).get().getNumber() && amount >0 && (paymentMethod.toLowerCase().equals("google pay") || paymentMethod.toLowerCase().equals("apple pay") || paymentMethod.toLowerCase().equals("visa") || paymentMethod.toLowerCase().equals("mastercard"))) {
           Products products = productsRepository.findById(id).get();
           int sumBuy = amount * products.getSum();
           String nameProduct = products.getName();
           buyInfoRepository.save(BuyInfo.builder().nameShop(nameShops).nameProduct(nameProduct).amount(amount).sumBuy(sumBuy).nameBuyer(nameBuyer).paymentMethod(paymentMethod.toLowerCase()).build());
           products.setNumber(products.getNumber() - amount);
           productsRepository.save(products);
       }
       else {
           ResponseEntity.status(400).build();
       }
    }
    //получение чека
    @GetMapping("/getCheck/{id}")
    public Optional<BuyInfo> getCheck(@PathVariable Integer id){
        return buyInfoRepository.findById(id);
    }
    @Transactional
    @PostMapping("/ofw")
    public void OrderFromWarehouses(@RequestParam Integer id,@RequestParam Integer idProductFromWarehouse, @RequestParam int number){
         if(productsRepository.findById(id).get().getNumber() + number <= 5000) {
             Products products = productsRepository.findById(id).get();
             products.setNumber(products.getNumber() + number);
             productsRepository.save(products);
             restTemplateServise.OrderFromWarehouses(idProductFromWarehouse, number,  products.getName(), products.getDescription());

         }
    }
}



