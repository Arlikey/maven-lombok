package currency_converter;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private final Map<Currency, Double> exchangeRates = new HashMap<>();

    public CurrencyConverter() {
        exchangeRates.put(Currency.USD, 1.0);
        exchangeRates.put(Currency.EUR, 0.86);
        exchangeRates.put(Currency.GBP, 0.75);
        exchangeRates.put(Currency.JPY, 148.46);
    }

    public double convert(double amount, Currency from, Currency to) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (!exchangeRates.containsKey(from) || !exchangeRates.containsKey(to)) {
            throw new IllegalArgumentException("Currency not supported.");
        }

        double inUsd = amount / exchangeRates.get(from);
        return inUsd * exchangeRates.get(to);
    }
}
