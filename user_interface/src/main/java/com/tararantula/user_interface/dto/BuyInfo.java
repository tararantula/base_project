package com.tararantula.user_interface.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BuyInfo {
	private Integer id;
	private String nameShop;
	private String nameProduct;
	private int amount;
	private int sumBuy;
	private String nameBuyer;
	private String paymentMethod;
}
