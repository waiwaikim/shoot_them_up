package _03objects.P8_14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Driver {

    public static void main(String[] args) {
        ArrayList<Country> countries = new ArrayList<Country>();
        // the following information is from the World Bank as of 2018 statistics
        countries.add( new Country("US", 327167434, 9831510));
        countries.add( new Country("China", 1392730000,	9562910));
        countries.add( new Country("Korea", 51635256,	100339));
        countries.add( new Country("Russia", 144478050,	17098250));
        countries.add( new Country("Hong Kong", 7451000,	1110));
        countries.add( new Country("Singapore", 5638676,	719));
        countries.add( new Country("South Africa", 57779622,	1219090));
        countries.add( new Country("Brazil", 209469333,	8515770));

        Collections.sort(countries, new Sortbyarea());
        System.out.println("The largest country by land mass: " + countries.get(0).getName());

        Collections.sort(countries, new Sortbypop());
        System.out.println("The most populous country: "+ countries.get(0).getName());

        Collections.sort(countries, new Sortbydensity());
        System.out.println("The most densely populated country: " + countries.get(0).getName());
    }
}
