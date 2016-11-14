package com.bjss.shopping.store;

import javax.money.MonetaryAmount;

public interface IShoppingCart {

    public void accept(ShoppingVisitor shoppingVisitor);

    public void setShoppingCart(ShoppingCart shoppingCart);

    public ShoppingCart getShoppingCart();
    
    /**
    *
    * @return <code>{@link MonetaryAmount}</code>, represent the products value.
    */
   MonetaryAmount getItemPrice(); 

}
