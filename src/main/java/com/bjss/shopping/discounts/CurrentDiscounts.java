package com.bjss.shopping.discounts;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CurrentDiscounts {

	private final Set<Discount> discounts = new HashSet<>();

	public Set<Discount> getDiscounts() {
		discounts.add(new AppleDiscount());
		discounts.add(new BreadDiscount());	
		return discounts;
	}
	
	
	
}
