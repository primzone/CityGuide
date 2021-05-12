package sesrvice;

import domain.City;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

public class CityFinder {

    public static String getCityWithMaxPopulation(List<City> listOfCities) throws FileNotFoundException {

        System.out.println("********************************МОДУЛЬ 3**************************************");
        //********Решение №1*****
        City maxPopulationCity = Collections.max(listOfCities, (o1, o2) -> o1.getPopulation() - o2.getPopulation());
        System.out.println("[" + listOfCities.indexOf(maxPopulationCity) + "] = " + maxPopulationCity.getPopulation());

        //********Решение №2*****
        City[] cities = new City[listOfCities.size()];
        for (int i = 0; i < listOfCities.size(); i++) {
            cities[i] = listOfCities.get(i);
        }

        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getPopulation() > max){
                max = cities[i].getPopulation();
                index = i;
            }
        }

        return "[" + index + "] = " + max;
    }

}
