package com.bjss.shopping.cmd;

import com.bjss.shopping.app.menu.MenuWireOption;
import com.bjss.shopping.store.IShoppingCart;
import com.bjss.shopping.store.ShoppingCart;
import com.bjss.shopping.store.calc.Calculator;
import org.springframework.stereotype.Component;

import java.util.List;

@MenuWireOption(value = "PriceBasket", description = ("price calculation command"), 
shortValue = ("pb"), isMandatory = true, isAvailable = true)
/**
 * This class has in charge the price calculation of the all product basket
 */
@Component("PriceBasket")
public class CmdPriceBasket implements Cmd<IShoppingCart> {

    @Override
    public void execute(final List<IShoppingCart> goods) {
        ShoppingCart shoppingCart = new ShoppingCart();
    	goods.stream().forEach(s->shoppingCart.addShoppingItem(s));

    	shoppingCart.accept(new Calculator());
    }

    @Override
    public String getName() {
        return "PriceBasket";
    }




}
