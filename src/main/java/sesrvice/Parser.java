package sesrvice;

import domain.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static List<City> getListOfCities(String filename) throws FileNotFoundException {
        System.out.println("********************************МОДУЛЬ 1**************************************");

        Scanner scanner = new Scanner(new File(filename)).useDelimiter(";");
        List<City> listOfCities = new ArrayList<>();

        //Как говорилось на встрече, учитывая, что входные данные всегда корректны )
        while (scanner.hasNext()){
            int id = scanner.nextInt();
            String name = scanner.next();
            String region= scanner.next();
            String district = scanner.next();
            int population = scanner.nextInt();
            int foundation = scanner.nextInt();
            listOfCities.add(new City(name, region, district, population, foundation));
        }

        System.out.println(listOfCities);
        return listOfCities;

    }

}
