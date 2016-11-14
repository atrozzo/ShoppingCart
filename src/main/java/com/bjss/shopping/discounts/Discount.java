package com.bjss.shopping.discounts;


import com.bjss.shopping.store.ShoppingCart;

/**
 * 
 * Discounts interface. 
 *
 */
public interface Discount {
	
	/**
	 * Apply discount on the passed ShoppingCart
	 * 
	 * @param shC {@link ShoppingCart}
	 */
	void applyDiscount(final ShoppingCart shC);

}
