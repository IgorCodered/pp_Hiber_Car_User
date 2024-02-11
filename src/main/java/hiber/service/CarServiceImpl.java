package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarDao carDao;

    @Override
    public void add(Car car) {
        carDao.add(car);
    }

    @Override
    public User getUserFromModelAndSeries(String model, int series) {
        return carDao.getUserFromModelAndSeries(model, series);
    }
}
