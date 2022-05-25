package com.store.online.Ecom.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartProductRemoveRequestDto {
	String productName;
}
