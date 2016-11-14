package com.bjss.shopping.store.calc;

import com.bjss.shopping.discounts.CurrentDiscounts;
import com.bjss.shopping.store.IShoppingCart;
import com.bjss.shopping.store.ShoppingCart;
import com.bjss.shopping.store.ShoppingVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.money.MonetaryAmount;


public class Calculator implements ShoppingVisitor {
    private final static Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    @Override
    public void visitShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.getShoppingItems().stream().forEach(s->s.accept(this));
        double subTotal = shoppingCart.getTotalBill().getNumber().doubleValue();
        LOGGER.info("SubTotal: {}" ,subTotal);
        // apply discounts:
        new CurrentDiscounts().getDiscounts().stream().forEach(s->s.applyDiscount(shoppingCart));
        if (shoppingCart.getTotalBillDiscounted().getNumber().doubleValue() > 0 ) {
            LOGGER.info("Total Price : {}" , shoppingCart.getTotalBillDiscounted().getNumber().doubleValue());
        }else{
            LOGGER.info("No offers available");
            LOGGER.info("Total price : {} ", subTotal);
        }
    }

    @Override
    public void visitCartItem(IShoppingCart cartItem) {
    	//sum price item prices and add to the shopping cart total bill
    	MonetaryAmount newValue = cartItem.getShoppingCart().getTotalBill().add(cartItem.getItemPrice());
        cartItem.getShoppingCart().setTotalBill(newValue);
    }

}
