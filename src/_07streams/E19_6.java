package _07streams;
import java.util.Currency;
import java.util.Set;
import java.util.stream.Stream;

public class E19_6 {


    public static void main(String[] args) {
        //The static getAvailableCurrencies method of the java.util.Currency class yields a set of Currency objects.
        // Turn it into a stream and transform it into a stream of the currency display names. Print them in sorted order.

        Set<Currency> myCurrencies = Currency.getAvailableCurrencies();
        Stream<String> currencyStream = myCurrencies.stream().map(t->t.getDisplayName()).sorted();
        currencyStream.forEach(e -> System.out.println(e));

    }
}
