package repository;

import domain.City;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityRepository {

    final String connectiobURL = "jdbc:h2:mem:database";
    private Connection connection ;
    private static String createQuery = "CREATE TABLE CITIES(\n" +
            " id INT PRIMARY KEY AUTO_INCREMENT,\n" +
            " name VARCHAR(255) UNIQUE ,\n" +
            " REGION VARCHAR(255),\n" +
            " DISTRICT VARCHAR(255),\n" +
            " population long,\n" +
            " foundation int\n" +
            ");";
    private static String insertQuery = "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Адыгейск', 'Адыгея', 'Южный', 12248, 1973);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Майкоп', 'Адыгея', 'Южный', 144246, 1857);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Горно-Алтайск', 'Алтай', 'Сибирский', 56928, 1830);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Алдан', 'Якутия', 'Дальневосточный', 21277, 1924);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Александровск-Сахалинский', 'Сахалинская область', 'Дальневосточный', 10613, 1869);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Амурск', 'Хабаровский край', 'Дальневосточный', 42977, 1958);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Абдулино', 'Оренбургская область', 'Приволжский', 20663, 1795);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Агидель', 'Башкортостан', 'Приволжский', 16365, 1980);\n" +
            "\n" +
            "INSERT INTO CITIES(NAME, REGION, DISTRICT, POPULATION, FOUNDATION)\n" +
            "VALUES ('Агрыз', 'Татарстан', 'Приволжский', 19299, 1646);";




    public Connection getConnection() throws SQLException {

        if (connection == null){
            connection = DriverManager.getConnection (connectiobURL, "","");
            return connection;
        }
        else return connection;

    }


    public void init(){

        try {
            if (connection == null){
                connection = getConnection();

                Statement statement = connection.createStatement();
                statement.execute(createQuery);
                statement.execute(insertQuery);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void addCity(City city){

        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into CITIES(NAME, DISTRICT, REGION, POPULATION, FOUNDATION)\n" +
                "VALUES (?, ?, ?, ?, ?);")){

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getDistrict());
            preparedStatement.setString(3, city.getRegion());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getFoundation());

            preparedStatement.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }


//        try (Statement statement = getConnection().createStatement("insert into CITIES()")) {
//            String insert = String.format("insert into CITIES(NAME, DISTRICT, REGION, POPULATION, FOUNDATION)\n" +
//                    "VALUES ('%s', '%s', '%s', %d, %d);", city.getName(), city.getDistrict(), city.getRegion(), city.getPopulation(), city.getFoundation());
//            statement.executeUpdate(insert);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }

    public void deleteCity(String name){



        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from cities where name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        try (Statement statement = getConnection().createStatement()) {
//            String deleteQuery = String.format("delete from cities where name = '%s'", name);
//            statement.executeUpdate(deleteQuery);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    public void updateCity(String name, City city){


        try (PreparedStatement preparedStatement = connection.prepareStatement("update cities set name = ?," +
                "REGION = ?, DISTRICT = ?, population = ?, foundation = ? where name = ?")){
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getRegion());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getFoundation());
            preparedStatement.setString(6, name);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        try (Statement statement = getConnection().createStatement()) {
//            String updateQuery = String.format("update cities set name = '%s'," +
//                    "REGION = '%s', DISTRICT = '%s', population = %d, foundation = %d where name = '%s'",
//                     city.getName(), city.getRegion(), city.getDistrict(), city.getPopulation(), city.getFoundation(), name);
//            statement.executeUpdate(updateQuery);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    public void findCity(String name){
        try (Statement statement = getConnection().createStatement()) {
            String findQuery = String.format("select * from cities where name = '%s'", name);

            ResultSet resultSet = statement.executeQuery(findQuery);

            while (resultSet.next()){
                City city = new City(resultSet.getString("name"),
                        resultSet.getString("REGION"),
                        resultSet.getString("DISTRICT"),
                        resultSet.getInt("population"),
                        resultSet.getInt("foundation"));
                System.out.println(city);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<City>  getSortedCitiesByName(){
        List<City> cityList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {

            String sql = "select * from CITIES order by NAME";

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                resultSet.getString("name");
                cityList.add(new City(resultSet.getString("name"),
                        resultSet.getString("REGION"),
                        resultSet.getString("DISTRICT"),
                        resultSet.getInt("population"),
                        resultSet.getInt("foundation")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  cityList;
    }


    public List<City>  getSortedCitiesByDistrictAndName(){
        List<City> cityList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {

            String sql = "select * from CITIES order by DISTRICT, NAME";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();


            cityList.add(new City(resultSet.getString("name"),
                    resultSet.getString("REGION"),
                    resultSet.getString("DISTRICT"),
                    resultSet.getInt("population"),
                    resultSet.getInt("foundation")));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cityList;
    }


    public String  getCityWithMaxPopulation(){
        List<City> cityList = new ArrayList<>();
        int id = 0;
        int maxpopulation = 0;
        try (Statement statement = getConnection().createStatement()) {

            String sql = "select id, population from CITIES order by population desc limit 1";

            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            id = resultSet.getInt("id");
            maxpopulation = resultSet.getInt("population");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "[" + id + "] - " + maxpopulation;

    }


    public List<City> getAllCities (){

        List<City> cityList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement()) {

            String sql = "select * from CITIES";

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                resultSet.getString("name");
                cityList.add(new City(resultSet.getString("name"),
                        resultSet.getString("REGION"),
                        resultSet.getString("DISTRICT"),
                        resultSet.getInt("population"),
                        resultSet.getInt("foundation")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return  cityList;
    }


    public Map<String, Integer> getCityCountByRegions() throws FileNotFoundException {

        Map<String, Integer> groupingMap = new HashMap<>();
        try (Statement statement = getConnection().createStatement()) {

            String sql = "select Region, count(name) as kolvo from CITIES group by Region";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                groupingMap.put(resultSet.getString("Region"), resultSet.getInt("kolvo"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return groupingMap;
    }


    public CityRepository() throws SQLException {

    }
}



