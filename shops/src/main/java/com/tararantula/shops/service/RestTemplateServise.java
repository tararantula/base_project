package com.tararantula.shops.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RestTemplateServise {
    @Autowired
    RestTemplate restTemplate;


    public void OrderFromWarehouses(Integer idProductFromWarehouse, int number, String name, String description) {
        restTemplate.exchange("http://warehouses_factories:7070/warehousesAndFactories/productDelivery/{idProductFromWarehouse}&{number}&{name}&{description}", HttpMethod.POST, null, String.class,idProductFromWarehouse, number, name, description);
    }
}
