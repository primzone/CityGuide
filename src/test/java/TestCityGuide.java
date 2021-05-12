import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCityGuide {

    @Before
    public void init(){

    }

    @Test
    public void testModule1() throws Exception {


        List<City> moduleList = CityGuide.module1("junittest.txt");


        List<City> checkList = new ArrayList<>();
        checkList.add(new City("Адыгейск", "Адыгея", "Южный", 12248,1973));
        checkList.add(new City("Майкоп", "Адыгея", "Южный", 144246,1857));
        checkList.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830));
        checkList.add(new City("Абаза", "Хакасия", "Сибирский", 17111,1867));
        Assert.assertEquals(checkList, moduleList);

    }

    @Test
    public void testModule2_1() throws Exception {

        List<City> moduleList = new ArrayList<>();
        moduleList.add(new City("Адыгейск", "Адыгея", "Южный", 12248,1973));
        moduleList.add(new City("Майкоп", "Адыгея", "Южный", 144246,1857));
        moduleList.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830));
        moduleList.add(new City("Алдан", "Якутия", "Дальневосточный", 21277,1924));
        moduleList.add(new City("Амурск", "Хабаровский край", "Дальневосточный", 42977,1958));
        moduleList = CityGuide.module2_1(moduleList);

        List<City> checkList = new ArrayList<>();
        checkList.add(new City("Адыгейск", "Адыгея", "Южный", 12248,1973));
        checkList.add(new City("Алдан", "Якутия", "Дальневосточный", 21277,1924));
        checkList.add(new City("Амурск", "Хабаровский край", "Дальневосточный", 42977,1958));
        checkList.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830));
        checkList.add(new City("Майкоп", "Адыгея", "Южный", 144246,1857));

        Assert.assertEquals(checkList, moduleList);


    }

    @Test
    public void testModule2_2() throws Exception {

        List<City> moduleList = new ArrayList<>();
        moduleList.add(new City("Адыгейск", "Адыгея", "Южный", 12248,1973));
        moduleList.add(new City("Майкоп", "Адыгея", "Южный", 144246,1857));
        moduleList.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830));
        moduleList.add(new City("Алдан", "Якутия", "Дальневосточный", 21277,1924));
        moduleList.add(new City("Амурск", "Хабаровский край", "Дальневосточный", 42977,1958));

        moduleList = CityGuide.module2_2(moduleList);

        List<City> checkList = new ArrayList<>();
        checkList.add(new City("Алдан", "Якутия", "Дальневосточный", 21277,1924));
        checkList.add(new City("Амурск", "Хабаровский край", "Дальневосточный", 42977,1958));
        checkList.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830));
        checkList.add(new City("Адыгейск", "Адыгея", "Южный", 12248,1973));
        checkList.add(new City("Майкоп", "Адыгея", "Южный", 144246,1857));

        Assert.assertEquals(checkList, moduleList);



    }

    @Test
    public void testModule3() throws Exception {



        List<City> checkList = new ArrayList<>();
        checkList.add(new City("Адыгейск", "Адыгея", "Южный", 12248,1973));
        checkList.add(new City("Майкоп", "Адыгея", "Южный", 144246,1857));
        checkList.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830));
        String module3 = CityGuide.module3(checkList);
        String check = "[1] = 144246";
        Assert.assertEquals(check, module3);

    }

    @Test
    public void testModule4() throws Exception {
        List<City> checkList = new ArrayList<>();
        checkList.add(new City("Адыгейск", "Адыгея", "Южный", 12248,1973));
        checkList.add(new City("Майкоп", "Адыгея", "Южный", 144246,1857));
        checkList.add(new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830));
        checkList.add(new City("Алдан", "Якутия", "Дальневосточный", 21277,1924));
        checkList.add(new City("Амурск", "Хабаровский край", "Дальневосточный", 42977,1958));

        Map<String, List<City>> moduleMap = CityGuide.module4(checkList);
        Map<String, List<City>> checkMap = new HashMap<>();
        checkMap.put("Адыгея", List.of(new City("Адыгейск", "Адыгея", "Южный", 12248,1973), new City("Майкоп", "Адыгея", "Южный", 144246,1857)));
        checkMap.put("Алтай", List.of((new City("Горно-Алтайск", "Алтай", "Сибирский", 56928,1830))));
        checkMap.put("Якутия", List.of((new City("Алдан", "Якутия", "Дальневосточный", 21277,1924))));
        checkMap.put("Хабаровский край", List.of((new City("Амурск", "Хабаровский край", "Дальневосточный", 42977,1958))));


        Assert.assertEquals(checkMap, moduleMap);


    }

}
