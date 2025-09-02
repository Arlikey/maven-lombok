package currency_converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyConverterTest {
    private CurrencyConverter converter = new CurrencyConverter();

    @Test
    void UsdToEur() {
        double expected = 100 * 0.86;
        double actual = converter.convert(100, Currency.USD, Currency.EUR);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void EurToUsd() {
        double expected = 1 / 0.86 * 92;
        double actual = converter.convert(92, Currency.EUR, Currency.USD);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void UsdToGbp() {
        double expected = 100 * 0.75;
        double actual = converter.convert(100, Currency.USD, Currency.GBP);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void GbpToJpy() {
        double expected = 1/0.75 * 148.46;
        double actual = converter.convert(1, Currency.GBP, Currency.JPY);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    void NegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(-10, Currency.USD, Currency.EUR));
    }

    @Test
    void UnsupportedCurrency() {
        assertThrows(IllegalArgumentException.class,
                () -> converter.convert(10, null, Currency.EUR));
    }
}