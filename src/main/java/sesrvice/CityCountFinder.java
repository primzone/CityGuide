package sesrvice;

import domain.City;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityCountFinder {

    public static Map<String, List<City>> getCityCountByRegions(List<City> listOfCities) throws FileNotFoundException {
        System.out.println("********************************МОДУЛЬ 4**************************************");

        Map<String, List<City>> groupingMap = listOfCities.stream().collect(Collectors.groupingBy(el -> el.getRegion()));
        return groupingMap;
    }

}
