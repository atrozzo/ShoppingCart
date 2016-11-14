package com.bjss.shopping.products;

import javax.money.MonetaryAmount;

import com.bjss.shopping.store.ShoppingCart;
import com.bjss.shopping.store.ShoppingVisitor;
import com.bjss.shopping.store.StoreContainerUnit;

/**
 * Common interface for all goods available in a shop.
 */
public interface Product {

    

    /**
     * The method define the way one or more products are aggregated.
     *
     * @return <code>{@link StoreContainerUnit}</code>
     */
    StoreContainerUnit getAggregationType();

    /**
     * Product description.
     *
     * @return
     */
    String getProductDescription();

    /**
     * This method give the information about how many of this porduct are available.
     * 
     * @return
     */
    Integer getQuantity();

    // Visitor part 

    public void accept(ShoppingVisitor shoppingVisitor);

    public void setShoppingCart(ShoppingCart shoppingCart);

    public ShoppingCart getShoppingCart();

}
