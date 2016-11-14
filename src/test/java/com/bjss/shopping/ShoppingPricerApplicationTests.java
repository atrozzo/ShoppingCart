package com.bjss.shopping;

import com.bjss.shopping.products.Apple;
import com.bjss.shopping.products.Bread;
import com.bjss.shopping.products.Soup;
import com.bjss.shopping.store.ShoppingCart;
import com.bjss.shopping.store.calc.Calculator;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.money.MonetaryAmount;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ShoppingPricerApplicationTests {


    ShoppingCart shoppingCart;
	MonetaryAmount totalBill;


    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void testShoppingCartIsEmpty() {
        assertThat(shoppingCart.getShoppingItems(), is(IsNull.notNullValue()));
    }



    @Test
    public void testPriceOnAppleNoDiscounts() {
        shoppingCart.addShoppingItem(new Apple());
        shoppingCart.addShoppingItem(new Apple());
        shoppingCart.accept(new Calculator());
        assertTrue(shoppingCart.getTotalBill().getNumber().intValue() ==2);
    }


    @Test
    public void testAppleDiscount() {
        shoppingCart.addShoppingItem(new Apple());
        shoppingCart.addShoppingItem(new Apple());
        shoppingCart.accept(new Calculator());
        assertTrue(shoppingCart.getTotalBill().getNumber().intValue() ==2);
        assertTrue(shoppingCart.getTotalBillDiscounted().getNumber().doubleValue() ==1.8);
    }


    @Test
    public void testBreadDiscount() {
        shoppingCart.addShoppingItem(new Soup());
        shoppingCart.addShoppingItem(new Soup());
        shoppingCart.addShoppingItem(new Bread());

        shoppingCart.accept(new Calculator());
        assertTrue(shoppingCart.getTotalBill().getNumber().doubleValue() ==2.1);
        assertTrue(shoppingCart.getTotalBillDiscounted().getNumber().doubleValue() ==1.7);
    }



    @Test
    public void testBreadDiscountWontWorkWithNoEnoughSoup() {
        shoppingCart.addShoppingItem(new Soup());
        shoppingCart.addShoppingItem(new Bread());

        shoppingCart.accept(new Calculator());

        assertTrue(shoppingCart.getTotalBill().getNumber().doubleValueExact() == 1.45);
        assertTrue(shoppingCart.getTotalBillDiscounted().getNumber().doubleValueExact() ==0);
    }



}
