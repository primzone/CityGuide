

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class CityGuide {



    public static void main(String[] args) throws FileNotFoundException {



        List<City> listOfCities = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int n = 0;
        System.out.println("Выберите модуль [1-4], Для выхода - любая другая цифра");
        while (scanner.hasNext()){
            n = scanner.nextInt();
            switch (n){
                case (1):
                    listOfCities = module1("test.txt");
                    break;
                case (2):
                    module2_1(listOfCities);
                    module2_2(listOfCities);
                    break;
                case (3):
                    System.out.println(module3(listOfCities));
                    break;
                case (4):
                    Map<String, List<City>> groupingMap = module4(listOfCities);
                    for (Map.Entry<String, List<City>> entry: groupingMap.entrySet()){
                        System.out.println(entry.getKey() + " - " + entry.getValue().size());
                    }
                    break;
                default:
                    System.out.println("выход");
                    break;
            }

        }



        //System.out.println(listOfCities);


    }


    public static List<City> module1(String filename) throws FileNotFoundException {

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


    public static List<City> module2_1(List<City> listOfCities) throws FileNotFoundException {
        System.out.println("********************************МОДУЛЬ 2**************************************");
        System.out.println("\n Вывод по сортировке №1 "); //лямбды
        listOfCities.sort((o1, o2) -> o1.name.toLowerCase().compareTo(o2.name.toLowerCase()));
        System.out.println(listOfCities);
        return listOfCities;
    }


    public static List<City> module2_2(List<City> listOfCities) throws FileNotFoundException {

        System.out.println("\n Вывод по сортировке №2"); // анонимные классы
        listOfCities.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {

                return (o1.district.compareTo(o2.district) != 0) ?  o1.district.compareTo(o2.district) : o1.name.compareTo(o2.name);

            }
        });
        System.out.println(listOfCities);

        return listOfCities;

    }
    public static String module3(List<City> listOfCities) throws FileNotFoundException {
        System.out.println("********************************МОДУЛЬ 3**************************************");

        //********Решение №1*****
        City maxPopulationCity = Collections.max(listOfCities, (o1, o2) -> o1.population - o2.population);
        System.out.println("[" + listOfCities.indexOf(maxPopulationCity) + "] = " + maxPopulationCity.population);

        //********Решение №2*****

        City[] cities = new City[listOfCities.size()];
        for (int i = 0; i < listOfCities.size(); i++) {
            cities[i] = listOfCities.get(i);
        }

        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].population > max){
                max = cities[i].population;
                index = i;
            }
        }
       // System.out.println("[" + index + "] = " + max);
        return "[" + index + "] = " + max;
    }

    public static Map<String, List<City>> module4(List<City> listOfCities) throws FileNotFoundException {
        System.out.println("********************************МОДУЛЬ 4**************************************");

        Map<String, List<City>> groupingMap = listOfCities.stream().collect(Collectors.groupingBy(el -> el.region));

        return groupingMap;

    }

}





class City{
    String name;
    String region;
    String district;
    int population, foundation;

    public City(String name, String region, String district, int population, int foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation=" + foundation +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (population != city.population) return false;
        if (foundation != city.foundation) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (region != null ? !region.equals(city.region) : city.region != null) return false;
        return district != null ? district.equals(city.district) : city.district == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + population;
        result = 31 * result + foundation;
        return result;
    }
}