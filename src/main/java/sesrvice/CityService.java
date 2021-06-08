package sesrvice;

import repository.CityRepository;

import java.sql.SQLException;

public class CityService {
    static CityRepository cityRepository;

    {
        try {
            cityRepository = new CityRepository();
            cityRepository.init();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
