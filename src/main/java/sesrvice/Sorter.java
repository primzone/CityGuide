package sesrvice;

import domain.City;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;

public class Sorter {

    public static List<City> getSortedCitiesByName(List<City> listOfCities) throws FileNotFoundException {
        System.out.println("********************************МОДУЛЬ 2**************************************");
        System.out.println("\n Вывод по сортировке №1: по названию города "); //лямбды
        listOfCities.sort((o1, o2) -> o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase()));
        System.out.println(listOfCities);
        return listOfCities;
    }

    public static List<City> getSortedCitiesByDistrictAndName(List<City> listOfCities) throws FileNotFoundException {

        System.out.println("\n Вывод по сортировке №2: по названию федерального округа и города"); // анонимные классы
        listOfCities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {

                return (o1.getDistrict().compareTo(o2.getDistrict()) != 0) ?  o1.getDistrict().compareTo(o2.getDistrict()) : o1.getName().compareTo(o2.getName());

            }
        });
        System.out.println(listOfCities);

        return listOfCities;
    }
}
