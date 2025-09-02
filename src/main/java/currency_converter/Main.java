package currency_converter;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        System.out.println(currencyConverter.convert(100, Currency.USD, Currency.EUR));
    }
}
