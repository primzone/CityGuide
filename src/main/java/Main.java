import domain.City;
import repository.CityRepository;
import sesrvice.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        List<City> listOfCities = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        CityRepository cityRepository = new CityRepository();
        cityRepository.init();

        int n = 0;
        System.out.println("Выберите модуль [1-4], Для выхода - любая другая цифра");
        Printer.printMenu();
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
                case (5):
                    cityRepository.addCity(new City("Санкт-Петербург", "Севере-Западный", "Ленинградская область", 1267322, 1703));
                    break;
                case (6):
                    cityRepository.deleteCity("Амурск");
                    break;
                case (7):
                    cityRepository.updateCity("Санкт-Петербург",
                            new City("Санкт-Петербург", "Севере-Западный", "Ленинградская область", 5000000, 1703));

                    break;
                case (8):
                    cityRepository.findCity("Санкт-Петербург");
                    break;
                case (10):

                    System.out.println(cityRepository.getAllCities());

                    break;
                case (11):

                    System.out.println(cityRepository.getSortedCitiesByName());

                    break;
                case (12):

                    System.out.println(cityRepository.getSortedCitiesByDistrictAndName());

                    break;
                case (13):

                    System.out.println(cityRepository.getCityWithMaxPopulation());

                    break;
                case (14):

                    System.out.println(cityRepository.getCityCountByRegions());

                    break;
                default:
                    System.out.println("выход");
                    break;
            }

        }
    }
}
