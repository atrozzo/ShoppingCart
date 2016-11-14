package com.bjss.shopping.store;

/**
 * This enum defines the possible currency available.
 * 
 * @author angelo.trozzo
 *
 */
public enum StoreCurrency {
    GBP,
    EUR,
    DEFAULT;

    /**
     * This method help to use one currency type without referring to a specific one. Kind of
     * locale.
     * 
     * @return {@link StoreCurrency}
     */
    public StoreCurrency getCurrentCurrency() {
        return StoreCurrency.GBP;
    }

}
