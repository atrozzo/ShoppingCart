package com.bjss.shopping.discounts;

import com.bjss.shopping.products.Apple;
import com.bjss.shopping.store.IShoppingCart;
import com.bjss.shopping.store.ShoppingCart;
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
 * This is a discount that apply for the product Apple. 
 * 
 * 
 */
public class AppleDiscount implements Discount {
	private final static Logger LOGGER = LoggerFactory.getLogger(AppleDiscount.class);

	// First of all needs to retrieve the current type defined from  with the discount interface. 
	//private final Type type = this.getClass().getGenericInterfaces()[0];

	private final BigDecimal discountPercentage = new BigDecimal("0.9"); // Discount. 
	private List<IShoppingCart> getProductsToDiscount(final ShoppingCart shC){
		return shC.getShoppingItems().stream().filter(s->s.getClass().
				equals(new Apple().getClass())).collect(Collectors.toList());
	}
	
	@Override
	public void applyDiscount(final ShoppingCart shC) {

            Optional<MonetaryAmount> discountedAMount = getDiscountedValue(discountPercentage, shC);
			if (discountedAMount.isPresent()){
				LOGGER.info("Apple 10% off : " + getDiscountedValue(new BigDecimal("0.1"), shC).get().getNumber());
                MonetaryAmount newDidcountedValue = shC.getTotalBillDiscounted().add(discountedAMount.get());
                shC.setTotalBillDiscounted(newDidcountedValue);
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
			List<MonetaryAmount> result =  getProductsToDiscount(shC).stream().
					map(s->s.getItemPrice().with(tenPercentOperator)).collect(Collectors.toList());
			
			return result.stream().reduce(MonetaryFunctions.sum());
	}



}
