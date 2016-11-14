package com.bjss.shopping.store;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements IShoppingCart {

    private  MonetaryAmount totalBill = Money.of(0,StoreCurrency.DEFAULT.getCurrentCurrency().name());
    		/*Monetary.getDefaultAmountFactory().setCurrency(StoreCurrency.DEFAULT.getCurrentCurrency().name())
            .setNumber(0).create();*/
    
    private  MonetaryAmount totalBillDiscounted =Money.of(0,StoreCurrency.DEFAULT.getCurrentCurrency().name());// Monetary.getDefaultAmountFactory().setCurrency(StoreCurrency.DEFAULT.getCurrentCurrency().name()).setNumber(0).create();

    private List<IShoppingCart> shoppingItems = new ArrayList<>();

    public void addShoppingItem(IShoppingCart shoppingCartItem) {
        shoppingCartItem.setShoppingCart(this);
        shoppingItems.add(shoppingCartItem);
    }

    public List<IShoppingCart> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<IShoppingCart> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    public MonetaryAmount getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(MonetaryAmount amount){
        totalBill = amount;
    }


    @Override
    public void accept(ShoppingVisitor shoppingVisitor) {
        shoppingVisitor.visitShoppingCart(this);

    }

    @Override
    public void setShoppingCart(ShoppingCart shoppingCart) {
        // nothing to do  here. 
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return this;
    }

	@Override
	public MonetaryAmount getItemPrice() {
		// does not count here.
     	return Money.of(0,StoreCurrency.DEFAULT.getCurrentCurrency().name());

	}

	public void setTotalBillDiscounted(MonetaryAmount discounted){
	    totalBillDiscounted = discounted;
    }

	public MonetaryAmount getTotalBillDiscounted() {
		return totalBillDiscounted;
	}

}
