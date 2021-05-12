import domain.City;
import sesrvice.CityCountFinder;
import sesrvice.CityFinder;
import sesrvice.Parser;
import sesrvice.Sorter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<City> listOfCities = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int n = 0;
        System.out.println("Выберите модуль [1-4], Для выхода - любая другая цифра");
        while (scanner.hasNext()){
            n = scanner.nextInt();
            switch (n){
                case (1):
                    listOfCities = Parser.getListOfCities("test.txt");
                    break;
                case (2):
                    Sorter.getSortedCitiesByName(listOfCities);
                    Sorter.getSortedCitiesByDistrictAndName(listOfCities);
                    break;
                case (3):
                    System.out.println(CityFinder.getCityWithMaxPopulation(listOfCities));
                    break;
                case (4):
                    Map<String, List<City>> groupingMap = CityCountFinder.getCityCountByRegions(listOfCities);
                    for (Map.Entry<String, List<City>> entry: groupingMap.entrySet()){
                        System.out.println(entry.getKey() + " - " + entry.getValue().size());
                    }
                    break;
                default:
                    System.out.println("выход");
                    break;
            }

        }
    }
}
