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
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
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
    //создание покупателей
    @GetMapping("/createBuyer")
    public void createBuyer(@RequestParam String name){
        buyerRepository.save(Buyer.builder().name(name).build());
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
//    @GetMapping("/getAllBuyByBuyer")
//    public List<Buy> getAllBuyByBuyer(@RequestParam Integer id){
//        return buyRepository.findAllByBuyerId(id);
//    }
//    @GetMapping("/createBuys")
//    public ResponseEntity<?> createBuys(@RequestParam String name, @RequestParam BigDecimal sum, @RequestParam Integer buyerId){
//        if(buyerRepository.existsById(buyerId)){
//            buyRepository.save(Buy.builder().name(name).sum(sum).buyerId(buyerId).build());
//            return ResponseEntity.ok().build();
//        }
//        else {
//            return ResponseEntity.status(401).build();
//        }
//    }
//    @GetMapping("/getById")
//    public Buy getById(@RequestParam Integer id){
//        return buyRepository.findById(id).get();
//    }
//    @GetMapping("/getByDate")
//    public Buy getByDate(@RequestParam Date buyingDate){
//        return buyRepository.findByBuyingDate(buyingDate);
//    }
//    @PostMapping("/createCheck")
//    public void createCheck(@RequestParam String name,@RequestParam int productId,@RequestParam int numberProduct){
//        chequeRepository.save(Cheque.builder().name(name).productId(productId).numberProduct(numberProduct).build());
//        restTemplateServise.putShops(productId,numberProduct);
//    }

}