package com.bjss.shopping.products;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.springframework.stereotype.Component;

import com.bjss.shopping.app.menu.MenuWireOption;
import com.bjss.shopping.store.IShoppingCart;
import com.bjss.shopping.store.ShoppingCart;
import com.bjss.shopping.store.ShoppingVisitor;
import com.bjss.shopping.store.StoreContainerUnit;
import com.bjss.shopping.store.StoreCurrency;

@MenuWireOption(value = "Milk", description = ("whole milk bio"), shortValue = ("mlk"))
@Component("Milk")
public class Milk implements Product, IShoppingCart {

    private ShoppingCart shoppingCart;

    @Override
    public MonetaryAmount getItemPrice() {
        return Monetary.getDefaultAmountFactory().setCurrency(StoreCurrency.DEFAULT.getCurrentCurrency().name())
                .setNumber(1.30).create();
    }

    @Override
    public StoreContainerUnit getAggregationType() {
        return StoreContainerUnit.BOTTLE;
    }

    @Override
    public String getProductDescription() {
        return "Milk";
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
