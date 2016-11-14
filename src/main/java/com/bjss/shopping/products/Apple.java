package com.bjss.shopping.products;

import com.bjss.shopping.app.menu.MenuWireOption;
import com.bjss.shopping.store.*;
import org.javamoney.moneta.Money;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.money.MonetaryAmount;

@MenuWireOption(value = "Apple", description = ("It s just an apple "), shortValue = ("apl"))
@Component("Apple")
@Scope("prototype")
public class Apple implements Product, IShoppingCart {

    private ShoppingCart shoppingCart;

    @Override
    public MonetaryAmount getItemPrice() {
    	
    	return Money.of(1,StoreCurrency.DEFAULT.getCurrentCurrency().name());
    }

    @Override
    public StoreContainerUnit getAggregationType() {
        return StoreContainerUnit.BAG;
    }

    @Override
    public String getProductDescription() {
        return "Apple";
    }

    @Override
    public void accept(ShoppingVisitor shoppingVisitor) {
        shoppingVisitor.visitCartItem(this);

    }

    @Override
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public Integer getQuantity() {
        // TODO Auto-generated method stub
        return 0;
    }

}
