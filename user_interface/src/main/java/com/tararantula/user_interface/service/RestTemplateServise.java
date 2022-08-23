package com.tararantula.user_interface.service;

import com.tararantula.user_interface.dto.BuyInfo;
import com.tararantula.user_interface.dto.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RestTemplateServise {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List<Shops>> shops() {
        return restTemplate.exchange("http://shops:9090/shop/getShops", HttpMethod.GET, null, new ParameterizedTypeReference<List<Shops>>() {
        });
    }

//    public void putShops(int id,int number) {
//        restTemplate.exchange("http://localhost:9090/putProducts/{id}&{number}", HttpMethod.POST, null, String.class, id, number);
//    }
    public void createBuy(String nameShops, Integer id, int amount, String nameBuyer, String paymentMethod) {
        restTemplate.exchange("http://shops:9090/shop/createBuy/{nameShops}&{id}&{amount}&{nameBuyer}&{paymentMethod}", HttpMethod.POST, null, String.class, nameShops, id, amount, nameBuyer, paymentMethod);
    }

    public ResponseEntity<Optional<BuyInfo>> check(Integer id) {
        return restTemplate.exchange("http://shops:9090/shop/getCheck/{id}", HttpMethod.GET, null, new ParameterizedTypeReference<Optional<BuyInfo>>() {
        }, id);
    }
}
