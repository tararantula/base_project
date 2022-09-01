package com.tararantula.user_interface.controllers;

import com.tararantula.user_interface.dto.BuyInfo;
import com.tararantula.user_interface.dto.Shops;
import com.tararantula.user_interface.entity.Buyer;
import com.tararantula.user_interface.repository.BuyRepository;
import com.tararantula.user_interface.repository.BuyerRepository;
import com.tararantula.user_interface.repository.ChequeRepository;
import com.tararantula.user_interface.service.RestTemplateServise;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class MainController {
    ChequeRepository chequeRepository;
    BuyRepository buyRepository;
    BuyerRepository buyerRepository;
    RestTemplateServise restTemplateServise;
    //список магазинов
    @GetMapping("/shops")
    public ResponseEntity<List<Shops>> getShops(){
        return restTemplateServise.shops();
    }
    //получение чека по id
    @GetMapping("getChecks/{id}")
    public ResponseEntity<Optional<BuyInfo>> getCheck(@PathVariable Integer id){
        return restTemplateServise.check(id);
    }
    //создание покупателя
    @GetMapping("/createBuyer")
    public Buyer createBuyer(@RequestParam String name){
        buyerRepository.save(Buyer.builder().name(name).build());
        return buyerRepository.findByName(name);
    }
    //получение покупателя по id
    @GetMapping("/getByIdBuyer")
    public Buyer getByIdBuyer(@RequestParam Integer id){
        return  buyerRepository.findById(id).get();}
    //создание покупки
    @PostMapping("/createBuy/{nameShops}&{id}&{amount}&{nameBuyer}&{paymentMethod}")
    public void createBuy(@PathVariable String nameShops, @PathVariable Integer id, @PathVariable int amount, @PathVariable String nameBuyer, @PathVariable String paymentMethod){
        if(buyerRepository.existsByName(nameBuyer)) {
            restTemplateServise.createBuy(nameShops, id, amount, nameBuyer, paymentMethod);
        }

    }
    //удаление покупателя
    @DeleteMapping("/deleteBuyer")
    public void deleteBuyer(@PathVariable Integer id){
        buyerRepository.deleteById(id);
    }
}