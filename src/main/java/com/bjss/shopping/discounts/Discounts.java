package com.bjss.shopping.discounts;

import java.util.List;
import java.util.stream.Collectors;

import com.bjss.shopping.store.IShoppingCart;
import com.bjss.shopping.store.ShoppingCart;

public abstract class Discounts {
		
	
	/**
	 * The method will return the the generic type T that each Discount has to specify in order to get assigned 
	 * to a specific product. 
	 * 
	 * @return {@link String} that is the type name of the class. 
	 */
	 String getGenericProductTypeClassName(){
			return this.getClass().getGenericInterfaces()[0].getTypeName();
	 }
	 
	 /**
	  * The method retrieve the product to discount available in the shopping cart. 
	  * 
	  * @param shC {@link ShoppingCart}
	  * @return {@link List} of {@link IShoppingCart} elements if available. 
	  */
	 List<IShoppingCart> getProductsToDiscount(final ShoppingCart shC){
			return shC.getShoppingItems().stream().filter(s->s.getClass().getName().
					equals(getGenericProductTypeClassName())).collect(Collectors.toList());
	 }

}
