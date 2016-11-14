package com.bjss.shopping.discounts;

import com.bjss.shopping.products.Bread;
import com.bjss.shopping.products.Soup;
import com.bjss.shopping.store.IShoppingCart;
import com.bjss.shopping.store.ShoppingCart;
import org.apache.commons.collections.CollectionUtils;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.MonetaryFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryOperator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Bread discount provide a special offer in case the suser will buy as well 2 tins of soup
 *
 */
public class BreadDiscount implements Discount {
	private final static Logger LOGGER = LoggerFactory.getLogger(AppleDiscount.class);
	private final BigDecimal discountPercentage = new BigDecimal("0.5"); // Discount.


	/**
	 * The method find the right {@link IShoppingCart} elements
	 *
	 * @param shC is the ShoppingCart
	 * @param typeTofind is the type to find .
	 * @return a {@link List} of {@link IShoppingCart}
	 */
	private List<IShoppingCart> getProductsToDiscount(final ShoppingCart shC, Class typeTofind){
		return shC.getShoppingItems().stream().filter(s->s.getClass().
				equals(typeTofind)).collect(Collectors.toList());
	}


	@Override
	public void applyDiscount(ShoppingCart shC) {
        List<IShoppingCart>  tinsOfSoup = getProductsToDiscount(shC, new Soup().getClass());
        if (CollectionUtils.isEmpty(tinsOfSoup) || tinsOfSoup.size() < 2 ){ // The offer applies just if you have 2 tins of soup.
            return;
        }else {
            Optional<MonetaryAmount> discountedAMount = getDiscountedValue(discountPercentage, shC);
            if (discountedAMount.isPresent()){
                LOGGER.info("Bread 50% off : {}" , discountedAMount.get());
                MonetaryAmount newDidcountedValue =  shC.getTotalBill().subtract(discountedAMount.get());
               // MonetaryAmount newDidcountedValue = shC.getTotalBillDiscounted().add(discountedAMount.get());
                shC.setTotalBillDiscounted(newDidcountedValue);
            }
        }
	}


    /**
     * This method help to calculate the right discount given a percentage.
     *
     * @param percentageToApply
     * @param shC is the {@link ShoppingCart}
     * @return {@link Monetary} {@link Optional}
     */
    private Optional<MonetaryAmount> getDiscountedValue(final BigDecimal percentageToApply, final ShoppingCart shC){

        MonetaryOperator tenPercentOperator = (MonetaryAmount amount) -> {
            BigDecimal baseAmount = amount.getNumber().numberValue(BigDecimal.class);
            BigDecimal tenPercent = baseAmount.multiply(percentageToApply); // Discount is just 10% , collect 90 % .
            return Money.of(tenPercent, amount.getCurrency());
        };
        List<MonetaryAmount> result =  getProductsToDiscount(shC,new Bread().getClass()).stream().
                map(s->s.getItemPrice().with(tenPercentOperator)).collect(Collectors.toList());

        return result.stream().reduce(MonetaryFunctions.sum());
    }
	
	
	
}
