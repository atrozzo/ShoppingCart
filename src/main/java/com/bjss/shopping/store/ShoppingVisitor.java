package com.bjss.shopping.store;

public interface ShoppingVisitor {

    public void visitShoppingCart(ShoppingCart shoppingCart);

    public void visitCartItem(IShoppingCart cartItem);
}
