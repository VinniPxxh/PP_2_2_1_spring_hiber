package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CarServiceImp implements CarService{

    @Autowired
    private CarDao carDao;

    @Transactional
    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars() {
        return carDao.listCars();
    }
}
